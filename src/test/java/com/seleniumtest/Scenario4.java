package com.seleniumtest;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import util.ScreenshotHelper;
import util.FileUtil;

public class Scenario4 extends BaseTest {    
    
    @Test(dataProvider = "scenario4-step1", dataProviderClass = DataProviderFromExcel.class)
    public void test4Step1(String beforeStep, String step, String URL, String buttonXPath, String scenarioName) throws Exception {
        // show library webpage
        driver.get(URL);
        Thread.sleep(5000);  // Sleep for 7 seconds
        ScreenshotHelper.takeScreenshot(driver, scenarioName, beforeStep); // before step 1
        
        // click Digital Repository Service
        driver.findElement(By.xpath(buttonXPath)).click();

        // switch to full page
        // driver.switchTo().defaultContent();

        // switch to new tab
        Thread.sleep(10000);
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
        Thread.sleep(500);
        ScreenshotHelper.takeScreenshot(driver, scenarioName, step); // after step 1
    }

    @Test(dataProvider = "scenario4-step2", dataProviderClass = DataProviderFromExcel.class)
    public void test4Step2(String step, String buttonXPath, String scenarioName) throws Exception {
        Thread.sleep(10000);
        // click Dataset
        driver.findElement(By.xpath(buttonXPath)).click();
        Thread.sleep(3000);
        ScreenshotHelper.takeScreenshot(driver, scenarioName, step); // after step 2
    }

    @Test(dataProvider = "scenario4-step3", dataProviderClass = DataProviderFromExcel.class)
    public void test4Step3(String step, String buttonXPath, String expected, String unexpected, String scenarioName) throws Exception {
        // click Dataset
        driver.findElement(By.xpath(buttonXPath)).click();
        boolean successDownload = FileUtil.waitForFileToDownload(downloadFilePath);
        // report
        if (successDownload)
            reportInfo(scenarioName, expected, expected);
        else
            reportInfo(scenarioName, expected, unexpected);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(successDownload, "File downloading failed.");
        softAssert.assertAll();
        ScreenshotHelper.takeScreenshot(driver, scenarioName, step); // after step 3
    }
}
