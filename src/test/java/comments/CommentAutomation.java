package comments;

import HandlingLobby.handleLobby;
import Login.loginFlow;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

/**
 * Class for automating comment posting functionality.
 */
public class CommentAutomation {

    private static final int number_Of_Comments = 5; // Set number of times you want to post comments

    /**
     * Method to post comments.
     *
     * @param driver The AppiumDriver instance.
     * @throws InterruptedException if the thread is interrupted.
     */
    public static void postComments(AppiumDriver<MobileElement> driver) throws InterruptedException {
        int desiredWidth = 300; // Desired width for screenshot
        int desiredHeight = 600; // Desired height for screenshot
        performLogin(driver);
        handleLobby(driver);
        clickTenSportsTile(driver); // Method call to click on TenSports tile
        startComments(driver); // Method call to start comments
        String screenshotBase64 = null; // Initializing screenshotBase64 variable

        // Loop to post comments
        for (int i = 0; i < number_Of_Comments; i++) {
            // Post a comment
            try {
                postComment(driver, "Comment " + (i + 1)); // Method call to post comment
                System.out.println("Comment " + (i + 1) + " Posted"); // Printing comment posted message
                Reporter.log("<b><p>Comment " + (i + 1) + " Posted</p></b>"); // Logging comment posted message

                // Taking screenshot and converting to Base64

            } catch(ElementNotVisibleException | NoSuchElementException | StaleElementReferenceException |
                    TimeoutException e){
                System.out.println("Exception occurred: " + e.getMessage()); // Printing exception message
            }

        }
        screenshotBase64 = takeScreenshotAsBase64(driver); // Method call to take screenshot
        Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + desiredWidth + "\" height=\"" + desiredHeight + "\" ></p>"); // Logging screenshot//        restartApp(driver);
    }

    /**
     * Method to handle lobby.
     *
     * @param driver The AppiumDriver instance.
     * @throws InterruptedException if the thread is interrupted.
     */
    private static void handleLobby(AppiumDriver<MobileElement> driver) throws InterruptedException {
        // handling lobby
        handleLobby newHandleLobby = new handleLobby();
        handleLobby.checkAndHandleLobby(driver);
    }

    /**
     * Method to post a comment.
     *
     * @param driver  The AppiumDriver instance.
     * @param comment The comment to post.
     */
    private static void postComment(AppiumDriver<MobileElement> driver, String comment) {
        // Locate the comment input field and enter the comment
        WebDriverWait wait = new WebDriverWait(driver, 100); // Initializing WebDriverWait
        MobileElement commentInput = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.spbtv.mobilinktv:id/etComment"))); // Waiting for comment input field
        commentInput.click(); // Clicking on comment input field
        commentInput.sendKeys("Pakstan zindabad"); // Entering comment text

        // Locate the comment post button and click it
        wait.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/ivSentBcg"))).click(); // Clicking on comment post button
    }

    /**
     * Method to restart the app.
     *
     * @param driver The AppiumDriver instance.
     */
    private static void restartApp(AppiumDriver<MobileElement> driver){
        driver.closeApp();
        driver.launchApp();
    }

    /**
     * Method to perform login.
     *
     * @param driver The AppiumDriver instance.
     * @throws InterruptedException if the thread is interrupted.
     */
    private static void performLogin(AppiumDriver<MobileElement> driver) throws InterruptedException {
        Login.loginFlow performlogin = new loginFlow();
        loginFlow.login(driver);
    }

    /**
     * Method to click on TenSports tile.
     *
     * @param driver The AppiumDriver instance.
     */
    public static void clickTenSportsTile(AppiumDriver<MobileElement> driver){
        WebDriverWait wait = new WebDriverWait(driver, 100); // Initializing WebDriverWait
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.spbtv.mobilinktv:id/relativeLayout\"])[2]"))).click(); // Clicking on TenSports tile
    }

    /**
     * Method to start comments.
     *
     * @param driver The AppiumDriver instance.
     */
    private static void startComments(AppiumDriver<MobileElement> driver){
        WebDriverWait wait = new WebDriverWait(driver, 100); // Initializing WebDriverWait
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView[@resource-id=\"com.spbtv.mobilinktv:id/toggle_fav\"]"))).click(); // Clicking to start comments
    }

    /**
     * Method to take screenshot and return as Base64.
     *
     * @param driver The AppiumDriver instance.
     * @return The screenshot as Base64.
     */
    private static String takeScreenshotAsBase64(AppiumDriver<MobileElement> driver) {
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // Taking screenshot
            byte[] screenshotBytes = Files.readAllBytes(screenshotFile.toPath()); // Reading screenshot bytes
            return Base64.getEncoder().encodeToString(screenshotBytes); // Converting to Base64
        } catch (IOException e) {
            throw new RuntimeException(e); // Throwing runtime exception for IO error
        }
    }
}
