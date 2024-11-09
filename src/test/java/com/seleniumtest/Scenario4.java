package com.seleniumtest;

import java.util.ArrayList;
// import java.io.File;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import util.ScreenshotHelper;

public class Scenario4 extends BaseTest {    
    
    @Test(dataProvider = "scenario4-step1", dataProviderClass = DataProviderFromExcel.class)
    public void testStep1(String beforeStep, String step, String URL, String buttonXPath, String scenarioName) throws Exception {
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
    public void testStep2(String step, String buttonXPath, String scenarioName) throws Exception {
        Thread.sleep(10000);
        // click Dataset
        driver.findElement(By.xpath(buttonXPath)).click();
        Thread.sleep(3000);
        ScreenshotHelper.takeScreenshot(driver, scenarioName, step); // after step 2
    }

    @Test(dataProvider = "scenario4-step3", dataProviderClass = DataProviderFromExcel.class)
    public void testStep3(String step, String buttonXPath, String scenarioName) throws Exception {
        // click Dataset
        driver.findElement(By.xpath(buttonXPath)).click();
        // File downloadedFile = new File(downloadFilePath + "/filename.extension");
        while (!downloadFilePath.isEmpty()) {
            Thread.sleep(1000); // Wait until the file appears
        }
        ScreenshotHelper.takeScreenshot(driver, scenarioName, step); // after step 2
    }
}
