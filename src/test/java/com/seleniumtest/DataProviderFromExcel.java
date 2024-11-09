package com.seleniumtest;

import org.testng.annotations.DataProvider;
import java.io.IOException;

import util.FileUtil;

public class DataProviderFromExcel {

    @DataProvider(name = "scenario4-step1")
    public Object[][] getExcelData4_1() throws IOException {
        return FileUtil.getExcelData(0);
    }

    @DataProvider(name = "scenario4-step2")
    public Object[][] getExcelData4_2() throws IOException {
        return FileUtil.getExcelData(1);
    }

    @DataProvider(name = "scenario4-step3")
    public Object[][] getExcelData4_3() throws IOException {
        return FileUtil.getExcelData(2);
    }

    @DataProvider(name = "scenario5-step0")
    public Object[][] getExcelData5_1() throws IOException {
        return FileUtil.getExcelData(3);
    }

    @DataProvider(name = "scenario5-step1-to-step3")
    public Object[][] getExcelData5_2() throws IOException {
        return FileUtil.getExcelData(4);
    }

    @DataProvider(name = "scenario5-step4")
    public Object[][] getExcelData5_3() throws IOException {
        return FileUtil.getExcelData(5);
    }

    @DataProvider(name = "scenario5-step5-step6")
    public Object[][] getExcelData5_4() throws IOException {
        return FileUtil.getExcelData(6);
    }

    @DataProvider(name = "scenario5-step7")
    public Object[][] getExcelData5_5() throws IOException {
        return FileUtil.getExcelData(7);
    }
}
