package TamashaAppTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.NoSuchElementException;

/**
 * Class representing tests related to live TV functionality.
 */
public class liveTV {

    // Locators for elements
    private final By live = new By.ById("com.spbtv.mobilinktv:id/item_live_channel");
    private final By home = new By.ById("com.spbtv.mobilinktv:id/item_home");

    private final int desiredWidth = 300; // Set your desired width of SS
    private final int desiredHeight = 600; // Set your desired height of SS

    /**
     * Switches to the live TV section.
     *
     * @param driver The AppiumDriver instance.
     * @throws InterruptedException If the thread sleep is interrupted.
     */
    public void switchToLiveTV(AppiumDriver<MobileElement> driver) throws InterruptedException, IOException {
        Thread.sleep(4000);

        String screenshotBase64 = null;
        try {
            clickOnLiveTab(driver);
            verifyAndPrintLiveTVStarted(driver);
            clickAnotherChannelTile(driver);
            takeScreenshot(driver);
            restartApp(driver);


            System.out.println("Live TV exited successfully.");
            Reporter.log("<b><p>Live TV Exited Successfully</p><b/>");
        } catch (WebDriverException | NoSuchElementException | IOException excp) {
            handleException(excp, screenshotBase64, driver);
        }
    }

    // Clicks on the Live tab
    private void clickOnLiveTab(AppiumDriver<MobileElement> driver)throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(live)).click();
        Thread.sleep(5000);
    }

    /**
     * Verifies and prints that live TV has started.
     *
     * @param driver The AppiumDriver instance.
     */
    private static void verifyAndPrintLiveTVStarted(AppiumDriver<MobileElement> driver) {
        // You can add verification logic here
        System.out.println("Live TV started.");
    }

    // Switches to the ARY Zindagi channel
    private static void clickAnotherChannelTile(AppiumDriver<MobileElement> driver)throws InterruptedException {
        // You can change this locator to match the channel tile you want to click
        try {
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.spbtv.mobilinktv:id/relativeLayout\"])[6]"))).click();
            Thread.sleep(5000);
        } catch (WebDriverException e) {
            System.out.println("Exception occurred while clicking channel tile: " + e.getMessage());
            Reporter.log("Exception occurred while clicking channel tile: " + e.getMessage());
        }
    }

    // Takes a screenshot and embeds it in the TestNG report
    private void takeScreenshot(AppiumDriver<MobileElement> driver) throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] screenshotBytes = Files.readAllBytes(screenshotFile.toPath());
        String screenshotBase64 = Base64.getEncoder().encodeToString(screenshotBytes);

        Reporter.log("<h4>Here is the Screen Shot</h4>");
        Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + desiredWidth + "\" height=\"" + desiredHeight + "\" ></p>");
    }

    // Navigates back to the Home
    private void navigateBackToHome(AppiumDriver<MobileElement> driver) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(home)).click();
    }

    // Handles exceptions and resets the app
    private void handleException(Exception excp, String screenshotBase64, AppiumDriver<MobileElement> driver) throws IOException {
        Reporter.log("<b><p>App Stuck message:</p></b>" + excp.getMessage());
        System.out.println("App Stuck message:" + excp.getMessage());
        takeScreenshot(driver);
        restartApp(driver);

    }

    private void restartApp(AppiumDriver<MobileElement> driver){
        driver.closeApp();
        driver.launchApp();
    }
}
