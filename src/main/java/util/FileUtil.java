package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.io.File;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileUtil {
    // reads data from an Excel file
    public static Object[][] getExcelData(int sheetNumber) throws IOException {
        FileInputStream fis = new FileInputStream("src/test/resources/test-data/testdata.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(sheetNumber); // sheet number
        
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
        
        Object[][] data = new Object[rowCount - 1][colCount];
        
        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = row.getCell(j).toString();
            }
        }
        workbook.close();
        fis.close();
        return data;
    }

    // check whether a download is started
    public static boolean checkDownloadStart(String downloadPath) {
        File downloadDir = new File(downloadPath);
        File latestFile = null;

        // Polling every second for the new file
        File[] files = downloadDir.listFiles(File::isFile);
        if (files != null && files.length > 0) {
            // Get the latest file in the directory based on last modified time
            latestFile = Arrays.stream(files)
                    .max(Comparator.comparingLong(File::lastModified))
                    .orElse(null);
            // Check if the latest file has non-zero size (indicating download start)
            if (latestFile != null && latestFile.length() > 0) {
                return true;
            }
        }

        try {
            Thread.sleep(1000); // Wait a second before checking again
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    // check whether a download is finished
    public static boolean waitForFileToDownload(String downloadPath) {
        File downloadDir = new File(downloadPath);
        File latestFile = null;
        int timeElapsed = 0, timeoutInSeconds = 600;

        // Polling every second for the new file
        while (timeElapsed < timeoutInSeconds) {
            File[] files = downloadDir.listFiles(File::isFile);
            if (files != null && files.length > 0) {
                // Get the latest file in the directory based on last modified time
                latestFile = Arrays.stream(files)
                        .max(Comparator.comparingLong(File::lastModified))
                        .orElse(null);
                String latestFileName = latestFile.getName();
                int latestFileNameLength = latestFileName.length();
                String fileSuffix = latestFileName.substring(latestFileNameLength-3, latestFileNameLength);
                // Check if the latest file has downloaded
                if (latestFile != null && fileSuffix.equals("zip")) {
                    return true;
                }
            }

            try {
                Thread.sleep(1000); // Wait a second before checking again
                timeElapsed++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
