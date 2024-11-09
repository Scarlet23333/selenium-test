package com.seleniumtest;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import util.ScreenshotHelper;
import util.AESUtil;

public class Scenario5 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        // options.addArguments("--auto-open-devtools-for-tabs");
        driver = new ChromeDriver(options);
    }

    @AfterClass
    public void tearDown() {
        // if (driver != null) {
        //     driver.quit();
        // }
    }

    @Test(dataProvider = "scenario5-step0", dataProviderClass = DataProviderFromExcel.class)
    public void testStep0(String step, String URL, String usernameTextBoxName, String passwordTextBoxName, String loginButtonID, 
                          String DUOButtonID, String username, String password, String secretKey) throws Exception {
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
        ScreenshotHelper.takeScreenshot(driver, "Scenario5", step); // before step 1
    }

    @Test(dataProvider = "scenario5-step1-to-step3", dataProviderClass = DataProviderFromExcel.class)
    public void testStep1to3(String step, String buttonXPath) throws Exception {
        driver.findElement(By.xpath(buttonXPath)).click();
        Thread.sleep(3000);
        ScreenshotHelper.takeScreenshot(driver, "Scenario5", step); // after step 1-3
    }

    @Test(dataProvider = "scenario5-step4", dataProviderClass = DataProviderFromExcel.class)
    public void testStep4(String step, String buttonXPath) throws Exception {
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.findElement(By.xpath(buttonXPath)).click();
        Thread.sleep(5000);
        ScreenshotHelper.takeScreenshot(driver, "Scenario5", step); // after step 4
    }

    @Test(dataProvider = "scenario5-step5-step6", dataProviderClass = DataProviderFromExcel.class)
    public void testStep5to6(String step, String calendarsXPath, String followingStep, String checkBoxXPath) throws Exception {
        WebElement element = driver.findElement(By.xpath(calendarsXPath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000); 
        ScreenshotHelper.takeScreenshot(driver, "Scenario5", step); // after step 5
        driver.switchTo().frame(element);
        driver.findElement(By.xpath(checkBoxXPath)).click();
        Thread.sleep(2000);
        ScreenshotHelper.takeScreenshot(driver, "Scenario5", followingStep); // after step 6
    }

    // @Test(dataProvider = "scenario5-step6", dataProviderClass = DataProviderFromExcel.class)
    // public void testStep6(String step, String checkBoxID) throws Exception {
    //     driver.findElement(By.xpath(checkBoxID)).click();
    //     Thread.sleep(1000); 
    //     ScreenshotHelper.takeScreenshot(driver, "Scenario5", step); // after step 6
    // }

    @Test(dataProvider = "scenario5-step7", dataProviderClass = DataProviderFromExcel.class)
    public void testStep7(String step, String buttonXPath) throws Exception {
        driver.switchTo().defaultContent();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        WebElement element = driver.findElement(By.xpath(buttonXPath));           
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(element.isDisplayed(), "'Add to My Calendar' button should be visible");
        softAssert.assertAll();
        ScreenshotHelper.takeScreenshot(driver, "Scenario5", step); // after step 7
    }
}
