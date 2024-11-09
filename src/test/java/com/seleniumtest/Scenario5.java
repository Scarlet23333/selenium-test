package com.seleniumtest;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;

import util.ScreenshotHelper;
import util.AESUtil;

public class Scenario5 extends BaseTest {

    @Test(dataProvider = "scenario5-step0", dataProviderClass = DataProviderFromExcel.class)
    public void test5Step0(String step, String URL, String usernameTextBoxName, String passwordTextBoxName, String loginButtonID, 
                          String DUOButtonID, String username, String password, String secretKey, String scenarioName) throws Exception {
        // login to student hub
        driver.get(URL);
        Thread.sleep(5000);  // Sleep for 5 seconds
        driver.findElement(By.name(usernameTextBoxName)).sendKeys(username);
        driver.findElement(By.id(loginButtonID)).click();
        String plainPassword = AESUtil.decrypt(password, secretKey);
        Thread.sleep(2000);
        driver.findElement(By.name(passwordTextBoxName)).sendKeys(plainPassword);
        driver.findElement(By.id(loginButtonID)).click();
        Thread.sleep(15000);
        driver.findElement(By.id(DUOButtonID)).click();
        Thread.sleep(5000);
        driver.findElement(By.id(loginButtonID)).click();
        Thread.sleep(5000);
        ScreenshotHelper.takeScreenshot(driver, scenarioName, step); // before step 1
    }

    @Test(dataProvider = "scenario5-step1-to-step3", dataProviderClass = DataProviderFromExcel.class)
    public void test5Step1to3(String step, String buttonXPath, String scenarioName) throws Exception {
        // click several buttons to go to the academic calender webpage
        driver.findElement(By.xpath(buttonXPath)).click();
        Thread.sleep(3000);
        if (step.equals("Step3")) {
            // switch to the new tab
            ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
            driver.switchTo().window(tabs.get(tabs.size() - 1));
        }
        Thread.sleep(500);
        ScreenshotHelper.takeScreenshot(driver, scenarioName, step); // after step 1-3
    }

    @Test(dataProvider = "scenario5-step4", dataProviderClass = DataProviderFromExcel.class)
    public void test5Step4(String step, String buttonXPath, String scenarioName) throws Exception {
        // click the calender button
        driver.findElement(By.xpath(buttonXPath)).click();
        Thread.sleep(5000);
        ScreenshotHelper.takeScreenshot(driver, scenarioName, step); // after step 4
    }

    @Test(dataProvider = "scenario5-step5-step6", dataProviderClass = DataProviderFromExcel.class)
    public void test5Step5to6(String step, String calendarsXPath, String followingStep, String checkBoxXPath, String scenarioName) throws Exception {
        WebElement element = driver.findElement(By.xpath(calendarsXPath));
        // scroll to show the calendars
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000); 
        ScreenshotHelper.takeScreenshot(driver, scenarioName, step); // after step 5

        // switch to the check box's parent frame
        driver.switchTo().frame(element);
        // uncheck a check box
        driver.findElement(By.xpath(checkBoxXPath)).click();
        Thread.sleep(2000);
        ScreenshotHelper.takeScreenshot(driver, scenarioName, followingStep); // after step 6
    }

    @Test(dataProvider = "scenario5-step7", dataProviderClass = DataProviderFromExcel.class)
    public void test5Step7(String step, String buttonXPath, String expected, String unexpected, String scenarioName) throws Exception {
        // switch to full page
        driver.switchTo().defaultContent();
        
        // scroll to bottem
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        WebElement element = driver.findElement(By.xpath(buttonXPath));

        // report
        if (element.isDisplayed())
            reportInfo(scenarioName, expected, expected);
        else
            reportInfo(scenarioName, expected, unexpected);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(element.isDisplayed(), "'Add to My Calendar' button should be visible");
        softAssert.assertAll();
        ScreenshotHelper.takeScreenshot(driver, scenarioName, step); // after step 7
    }
}
