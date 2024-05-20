package Miniclip;

import HandlingLobby.handleLobby;
import Login.loginFlow;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.NoSuchElementException;

public class reelsMiniclip {

    public static void reel(AppiumDriver<MobileElement> driver)throws InterruptedException{
        try {
            performLogin(driver);
            handleLobby(driver);
            clickReels(driver);
            for (int i = 0; i<3; i++){
                // Run swipeUp three times
                swipeUp(driver);

            }
            for (int i = 0; i<3; i++){
                // Run swipeDown three times
                swipeDown(driver);
            }
            restartApp(driver);

        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
            System.out.println(e.getMessage());
            Reporter.log(e.getMessage());
            restartApp(driver);
        }
    }

    private static void restartApp(AppiumDriver<MobileElement> driver){
        driver.closeApp();
        driver.launchApp();
    }
    private static void clickReels(AppiumDriver<MobileElement> driver){

        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.spbtv.mobilinktv:id/tv_item_6"))).click();

    }
    private static void swipeUp(AppiumDriver<MobileElement> driver) throws InterruptedException {

        String screenshotBase64 = null; // Initializing screenshotBase64 variable
        int desiredWidth = 300; // Desired width for screenshot
        int desiredHeight = 600; // Desired height for screenshot

        Reporter.log("<h3><p>Swiping Up</p></h3>");
        WebElement bottomElement = driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollToEnd(1)"));

        int bottomY = bottomElement.getLocation().getY();
        int screenHeight = driver.manage().window().getSize().getHeight();

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(0, bottomY)).moveTo(PointOption.point(0, screenHeight / 2))
                .release()
                .perform();
        System.out.println("Before");
        Reporter.log("<b>Before (Liking/Unliking the Reel)</b>");
        screenshotBase64 = takeScreenshot(driver); // Method call to take screenshot
        Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + desiredWidth + "\" height=\"" + desiredHeight + "\" ></p>"); // Logging screenshot

        Thread.sleep(10000); // Wait for 10 seconds
        LikeReel(driver);
        System.out.println("After");
        Reporter.log("<b>After (Liking/Unliking the Reel)</b>");
        screenshotBase64 = takeScreenshot(driver); // Method call to take screenshot
        Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + desiredWidth + "\" height=\"" + desiredHeight + "\" ></p>"); // Logging screenshot
        playPause(driver);

    }

    private static String takeScreenshot(AppiumDriver<MobileElement> driver) throws InterruptedException {

        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // Taking screenshot
            byte[] screenshotBytes = Files.readAllBytes(screenshotFile.toPath()); // Reading screenshot bytes
            return Base64.getEncoder().encodeToString(screenshotBytes); // Converting to Base64
        } catch (IOException e) {
            throw new RuntimeException(e); // Throwing runtime exception for IO error
        }
    }

        private static  void performLogin(AppiumDriver<MobileElement> driver) throws InterruptedException {

    Login.loginFlow performlogin = new loginFlow();
    loginFlow.login(driver);


    }
    private static void handleLobby(AppiumDriver<MobileElement> driver) throws InterruptedException {
        // handling lobby
        handleLobby newHandleLobby = new handleLobby();
        handleLobby.checkAndHandleLobby(driver);

    }


    private static void swipeDown(AppiumDriver<MobileElement> driver) throws InterruptedException {
        String screenshotBase64 = null; // Initializing screenshotBase64 variable
        int desiredWidth = 300; // Desired width for screenshot
        int desiredHeight = 600; // Desired height for screenshot
        Reporter.log("<h3><p>Swiping Down</p></h3>");
        WebElement topElement = driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollToBeginning(1)"));

        int topY = topElement.getLocation().getY();
        int screenHeight = driver.manage().window().getSize().getHeight();

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(0, screenHeight / 2)).moveTo(PointOption.point(0, topY))
                .release()
                .perform();
        System.out.println("Before");
        Reporter.log("Before");
        screenshotBase64 = takeScreenshot(driver); // Method call to take screenshot
        Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + desiredWidth + "\" height=\"" + desiredHeight + "\" ></p>"); // Logging screenshot
        Thread.sleep(10000);
        LikeReel(driver);
        System.out.println("After");
        Reporter.log("After");
        screenshotBase64 = takeScreenshot(driver); // Method call to take screenshot
        Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + desiredWidth + "\" height=\"" + desiredHeight + "\" ></p>"); // Logging screenshot
        playPause(driver);

    }

    private static void LikeReel(AppiumDriver<MobileElement> driver) throws InterruptedException{

        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.spbtv.mobilinktv:id/iv_like"))).click();

    }

    private static void playPause(AppiumDriver<MobileElement> driver) throws InterruptedException{

        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.spbtv.mobilinktv:id/ly_volume"))).click();

    }


    }


