package com.seleniumtest;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import java.io.FileInputStream;
import java.io.IOException;

public class DataProviderFromExcel {

    // reads data from an Excel file
    public Object[][] getExcelData(int sheetNumber) throws IOException {
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

    @DataProvider(name = "scenario4-step1")
    public Object[][] getExcelData4_1() throws IOException {
        return getExcelData(0);
    }

    @DataProvider(name = "scenario4-step2")
    public Object[][] getExcelData4_2() throws IOException {
        return getExcelData(1);
    }

    @DataProvider(name = "scenario4-step3")
    public Object[][] getExcelData4_3() throws IOException {
        return getExcelData(2);
    }

    @DataProvider(name = "scenario5-step0")
    public Object[][] getExcelData5_1() throws IOException {
        return getExcelData(3);
    }

    @DataProvider(name = "scenario5-step1-to-step3")
    public Object[][] getExcelData5_2() throws IOException {
        return getExcelData(4);
    }

    @DataProvider(name = "scenario5-step4")
    public Object[][] getExcelData5_3() throws IOException {
        return getExcelData(5);
    }

    @DataProvider(name = "scenario5-step5-step6")
    public Object[][] getExcelData5_4() throws IOException {
        return getExcelData(6);
    }

    @DataProvider(name = "scenario5-step7")
    public Object[][] getExcelData5_5() throws IOException {
        return getExcelData(7);
    }
}
