package FanPulse;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class fanPulseHowitWorks {

    static By howItworkslink = new By.ByXPath("//android.widget.Image[@text=\"question\"]");
    static By getTextofHowitworks = new By.ByXPath("//android.widget.TextView[@text=\"During live PSL matches, the commentator announces questions, and viewers answer through the Tamasha app and Jazz Cricket App.\"]");
    public static void howItworks(AppiumDriver<MobileElement> driver) throws IOException {

        fanPulseLeaderboardLastUpdate.clickingJazzIcon(driver);
        clickHowitWorks(driver);
        getText(driver);
        takeScreenshot(driver);

    }

    public static void clickHowitWorks(AppiumDriver<MobileElement> driver){

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(howItworkslink)).click();

    }
    public static void getText(AppiumDriver<MobileElement> driver){

        WebDriverWait wait = new WebDriverWait(driver, 30);
        MobileElement textHowitWorks = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(getTextofHowitworks));
        String printText = textHowitWorks.getText();
        System.out.println(printText);

    }

    private static void takeScreenshot(AppiumDriver<MobileElement> driver) throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] screenshotBytes = Files.readAllBytes(screenshotFile.toPath());
        String screenshotBase64 = Base64.getEncoder().encodeToString(screenshotBytes);

        Reporter.log("<h4>Here is the Screen Shot</h4>");
        // Set your desired width of SS
        int desiredWidth = 300;
        // Set your desired height of SS
        int desiredHeight = 600;
        Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + desiredWidth + "\" height=\"" + desiredHeight + "\" ></p>");
    }

}
