package TamashaAppTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.NoSuchElementException;

public class screenOrient {

    // Locators for elements
    private final By clickTile = new By.ByXPath("(//android.widget.RelativeLayout[@resource-id=\"com.spbtv.mobilinktv:id/relativeLayout\"])[1]");
    private final By hozScr = new By.ById("com.spbtv.mobilinktv:id/exo_fullscreen_icon");
    private final By verScr = new By.ById("com.spbtv.mobilinktv:id/exo_fullscreen_button");
    private final By pauseVid = new By.ById("com.spbtv.mobilinktv:id/exo_pause");
    private final By verPlayBtn = new By.ById("com.spbtv.mobilinktv:id/exo_play");
    private final By miniVid = new By.ById("com.spbtv.mobilinktv:id/back");
    private final By closePlayer = new By.ById("com.spbtv.mobilinktv:id/iv_motion_close");

    // Main method to handle orientation and video player actions
    public void handleOrientation(AppiumDriver<MobileElement> driver) throws InterruptedException {
        Thread.sleep(5000);

        try {
            clickOnTile(driver);
            switchToFullScreen(driver);
            verifyOrientation(driver);
            pauseVideoPlayback(driver);
            minimizeVideo(driver);
            closePlayer(driver);

            System.out.println("Live Streaming Ended!");
            Reporter.log("<b>Live Streaming Ended</b>");
            driver.closeApp();
            driver.launchApp();
        } catch (WebDriverException | NoSuchElementException excp) {
            handleException(excp, driver);
        }
    }

    // Clicks on a specific tile
    private void clickOnTile(AppiumDriver<MobileElement> driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(clickTile)).click();
    }

    // Switches to full screen
    private void switchToFullScreen(AppiumDriver<MobileElement> driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(hozScr)).click();
    }

    // Verifies the device orientation and interacts with a specific element
    private void verifyOrientation(AppiumDriver<MobileElement> driver) {
        ScreenOrientation orientation = driver.getOrientation();

        if (orientation == ScreenOrientation.LANDSCAPE) {
            System.out.println("Device is in Landscape mode");
        } else {
            System.out.println("Device is in Portrait mode");
        }

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(verScr)).click();
    }

    // Pauses video playback
    private void pauseVideoPlayback(AppiumDriver<MobileElement> driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(pauseVid)).click();

        WebDriverWait waitPlayButton = new WebDriverWait(driver, 20);
        boolean isPlayButtonPresent = waitPlayButton.until(ExpectedConditions.presenceOfElementLocated(verPlayBtn)).isEnabled();

        if (isPlayButtonPresent) {
            System.out.println("Video playback is stopped.");
        } else {
            System.out.println("Video playback might not have been stopped.");
        }
    }

    // Minimizes video
    private void minimizeVideo(AppiumDriver<MobileElement> driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(miniVid)).click();
    }

    // Closes the player
    private void closePlayer(AppiumDriver<MobileElement> driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(closePlayer)).click();
    }

    // Handles exceptions and resets the app
    private void handleException(Exception excp, AppiumDriver<MobileElement> driver) {
        Reporter.log("<b>App Stuck message:</b> " + excp.getMessage());
        System.out.println("App Stuck message:" + excp.getMessage());
        driver.closeApp();
        driver.launchApp();
    }
}
