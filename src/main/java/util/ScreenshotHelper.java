package util;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class ScreenshotHelper {
    public static void takeScreenshot(WebDriver driver, String scenarioName, String step) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File("target/screenshots/" + scenarioName + "/" + step + ".png");
            FileUtils.copyFile(screenshot, destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
