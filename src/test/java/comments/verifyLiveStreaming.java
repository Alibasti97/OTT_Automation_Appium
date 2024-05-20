package comments;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.NoSuchElementException;

public class verifyLiveStreaming {

    public static void verifyLiveStreaming(AppiumDriver<MobileElement> driver){

        clickLiveStreaming(driver);
        takescreenshot(driver);
        restartApp(driver);
    }

    public static void clickLiveStreaming(AppiumDriver<MobileElement> driver){
        WebDriverWait wait = new WebDriverWait(driver, 100); // Initializing WebDriverWait
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.spbtv.mobilinktv:id/relativeLayout\"])[1]"))).click(); // Clicking on TenSports tile
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.spbtv.mobilinktv:id/rv_bottom_live_channels"))).isDisplayed();
    }
    private static void takescreenshot(AppiumDriver<MobileElement> driver) {
        final int DESIRED_WIDTH = 300;
        final int DESIRED_HEIGHT = 600;
        String screenshotBase64 = "";
        try {
            File screenshotFile = driver.getScreenshotAs(OutputType.FILE);
            byte[] screenshotBytes = Files.readAllBytes(screenshotFile.toPath());
            screenshotBase64 = Base64.getEncoder().encodeToString(screenshotBytes);

        } catch (IOException | NoSuchElementException | TimeoutException ex) {
            ex.printStackTrace();

        }

        Reporter.log("<h4>Here is the Screen Shot</h4>");
        Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + DESIRED_WIDTH + "\" height=\"" + DESIRED_HEIGHT + "\" ></p>");
    }



private static void restartApp(AppiumDriver<MobileElement> driver){
        driver.closeApp();
        driver.launchApp();

}
}
