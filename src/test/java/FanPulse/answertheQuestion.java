package FanPulse;

import Login.loginFlow;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class answertheQuestion {

    public static void automateFanpulse(AppiumDriver<MobileElement> driver) throws InterruptedException {
        try {
            // 1. Click on TenSports Tile from home page
            launchLiveStreaming(driver);
            clickStartQuizButton(driver);
            selectTeam(driver);
            clickFirstChoice(driver);

        } catch (ElementNotVisibleException | NoSuchElementException | StaleElementReferenceException e) {
            System.out.println(e.getCause());
            Reporter.log(e.getMessage());
            restartApp(driver);
            // Handle any exceptions here
        } catch (InterruptedException | TimeoutException e) {
            System.out.println(e.getCause());
            Reporter.log(e.getMessage());
            restartApp(driver);
        }
    }

    private static void launchLiveStreaming(AppiumDriver<MobileElement> driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.spbtv.mobilinktv:id/relativeLayout\"])[1]")))
                .click();
    }

//    private static  void performLogin(AppiumDriver<MobileElement> driver) throws InterruptedException {
//
//    Login.loginFlow performlogin = new loginFlow();
//    performlogin.login(driver);
//
//
//    }

//    private static void pauseScriptForManualOTPEntry(AppiumDriver<MobileElement> driver) {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, 100);
//            wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/text1"))).click();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//
//        }
//    }

    private static void clickStartQuizButton(AppiumDriver<MobileElement> driver) {
        // Click on a button to start quiz
        WebDriverWait wait = new WebDriverWait(driver, 20); // Initializing WebDriverWait
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.spbtv.mobilinktv:id/ivFanPulse"))).click();
    }


    private static void clickStartButton(AppiumDriver<MobileElement> driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@resource-id=\"start-answer-question\"]"))).click();
// //android.widget.Button[@resource-id="start-answer-question"]
    }

    private static void getTextifQuestionNotAvailable(AppiumDriver<MobileElement> driver){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@resource-id=\"question-inactive\"]")));
        String elementText = element.getText();
        System.out.println(elementText);
        Reporter.log("<p>" + elementText + "</p>");

    }


    private static void clickFirstChoice(AppiumDriver<MobileElement> driver) throws InterruptedException {
        int desiredWidth = 300;
        int desiredHeight = 600;
        String screenshotBase64 = null;

        try {
            Thread.sleep(5000); // Adding a short delay

            WebDriverWait wait = new WebDriverWait(driver, 20);

            // Find the first choice element using XPath
            WebElement firstChoice = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@resource-id='0']")));
            // Check if the first choice exists in the DOM
            if (firstChoice != null) {

                // Click on the first choice
                firstChoice.click();
                Thread.sleep(5000);
                // Capture screenshot after clicking
                Reporter.log("<b>Answered the Question</b>");
                screenshotBase64 = takeScreenshotAsBase64(driver);
                Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + desiredWidth + "\" height=\"" + desiredHeight + "\" ></p>");
                clickNextAfterAnswering(driver);
                // Go to Leader Board Window.
                GoToLeaderBoard(driver);
                Thread.sleep(5000);
                Reporter.log("<b>Leaderboard</b>");
                screenshotBase64 = takeScreenshotAsBase64(driver);
                Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + desiredWidth + "\" height=\"" + desiredHeight + "\" ></p>");
                restartApp(driver);
            } else {
                // If the first choice doesn't exist, log and handle accordingly
                screenshotBase64 = takeScreenshotAsBase64(driver);
                Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + desiredWidth + "\" height=\"" + desiredHeight + "\" ></p>");
                restartApp(driver);
            }
        } catch (InterruptedException | TimeoutException | NoSuchElementException | ElementNotVisibleException e) {
            // Handle exceptions
            Reporter.log("<b>Fan Pulse Question not Available</b>");
            screenshotBase64 = takeScreenshotAsBase64(driver);
            Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + desiredWidth + "\" height=\"" + desiredHeight + "\" ></p>");
            restartApp(driver);
        }
    }

    private static void restartApp(AppiumDriver<MobileElement> driver) throws InterruptedException{
        driver.closeApp();
        driver.launchApp();
    }
    private static void selectTeam(AppiumDriver<MobileElement> driver){

        try {
                clickStartButton(driver);
        } catch (WebDriverException e) {
            System.out.println("Exception occurred");
        }

    }

//    private static void selectTeamName(AppiumDriver<MobileElement> driver){
//        WebDriverWait wait = new WebDriverWait(driver, 20);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.view.View[@resource-id=\"root\"])[2]/android.view.View/android.view.View[3]"))).click();
//
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@text=\"DONE\"]"))).click();
//
//    }

//    private static boolean isElementPresent(AppiumDriver<MobileElement> driver, String locator) {
//        try {
//            driver.findElement(By.xpath(locator));
//            return true;
//        } catch (org.openqa.selenium.NoSuchElementException e) {
//            return false;
//        }
//    }

    private static String takeScreenshotAsBase64(AppiumDriver<MobileElement> driver) {
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] screenshotBytes = Files.readAllBytes(screenshotFile.toPath());
            return Base64.getEncoder().encodeToString(screenshotBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    private static void clickButton(AppiumDriver<MobileElement> driver, String locator) {
//        // Click on the button
//        WebDriverWait wait = new WebDriverWait(driver, 20);
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator))).click();
//    }

    private static void clickNextAfterAnswering(AppiumDriver<MobileElement> driver){
        // Clicking the Next Button After Answering the Question
        WebDriverWait wait = new WebDriverWait(driver, 20); // Initializing WebDriverWait
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@text=\"Next\"]"))).click();
    }
    private static void GoToLeaderBoard(AppiumDriver<MobileElement> driver) {
        // Close the Quiz window
        WebDriverWait wait = new WebDriverWait(driver, 20); // Initializing WebDriverWait
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@resource-id=\"leaderboard-button\"]"))).click();

    // //android.widget.Button[@resource-id="leaderboard-button"]
    }

}


