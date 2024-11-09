package com.seleniumtest;

import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {
    protected static ExtentReports extent;
    protected ExtentTest test;
    protected WebDriver driver;
    protected static String downloadFilePath;

    @BeforeSuite
    public void setUp() {
        // setup reporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        // setup download file path
        downloadFilePath = Paths.get("target", "downloads").toAbsolutePath().toString();
        File downloadDir = new File(downloadFilePath);
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }
        // set chrome driver path
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver-win64/chromedriver.exe");
    }

    @AfterSuite
    public void reportGenerate() {
        extent.flush();
    }

    @BeforeClass
    public void setUpClass() {
        // Create ChromeOptions object
        ChromeOptions options = new ChromeOptions();

        // set up automatic download without confirm
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", downloadFilePath);
        options.setExperimentalOption("prefs", prefs);
        
        // set the browser start in max size
        options.addArguments("start-maximized");
        // options.addArguments("--auto-open-devtools-for-tabs");
        
        driver = new ChromeDriver(options);
    }

    @AfterClass
    public void tearDown() {
        // stop the driver
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void setupTest(Method method) {
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void logTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else {
            test.skip("Test skipped");
        }
    }

    public void reportInfo(String scenarioName, String expected, String actual) {
        // report info
        test.info("Scenario: " + scenarioName);
        test.info("Expected: " + expected);
        test.info("Actual: " + actual);
    }
}
