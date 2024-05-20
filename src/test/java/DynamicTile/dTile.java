package DynamicTile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.windows.PressesKeyCode;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.NoSuchElementException;

import static java.lang.StringTemplate.STR;

public class dTile {

    private static final int DESIRED_WIDTH = 600;
    private static final int DESIRED_HEIGHT = 300;

    public static void VerifyTile(AppiumDriver<MobileElement> driver)throws InterruptedException{

        try {
            Thread.sleep(10000);
            Reporter.log("<p>Here is the Screen Shot of the Dynamic Tile</p>");
            TakeScreenShotofDynamicTile(driver);
            ClickonDynamicTile(driver);
            Thread.sleep(5000);
            System.out.println("PSL Streaming Started");
            // Simulate pressing the back button
            driver.navigate().back();
            closeMotionScreen(driver);
            System.out.println("Live Streaming Closed!!!");

        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
            System.out.println("Exception is: " + e.getMessage());
            Reporter.log("Exception is: " + e.getMessage());
        }


    }

    private static void TakeScreenShotofDynamicTile(AppiumDriver<MobileElement> driver){
        String screenshotBase64 = null;
        WebDriverWait wait = new WebDriverWait(driver, 50);
        MobileElement DynamicTileView = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.spbtv.mobilinktv:id/actionBtn")));
        screenshotBase64 = takeScreenshotAsBase64(driver);
        Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + DESIRED_WIDTH + "\" height=\"" + DESIRED_HEIGHT + "\" ></p>");
    }

    private static void closeMotionScreen(AppiumDriver<MobileElement> driver) throws InterruptedException{

        WebDriverWait wait = new WebDriverWait(driver, 50);
        MobileElement closeElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.spbtv.mobilinktv:id/iv_motion_close")));
        closeElement.isDisplayed();
        closeElement.click();

    }
    private static void ClickonDynamicTile(AppiumDriver<MobileElement> driver){

        WebDriverWait wait = new WebDriverWait(driver, 50);
        MobileElement dynamicTile = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.spbtv.mobilinktv:id/actionBtn")));
        dynamicTile.isDisplayed();
        dynamicTile.click();

    }

    private static String takeScreenshotAsBase64(AppiumDriver<MobileElement> driver) {
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] screenshotBytes = Files.readAllBytes(screenshotFile.toPath());
            return Base64.getEncoder().encodeToString(screenshotBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}