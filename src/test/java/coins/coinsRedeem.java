package coins;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.NoSuchElementException;

/**
 * Class for redeeming coins.
 */
public class coinsRedeem {

    // Desired width and height for the screenshot
    int DESIRED_WIDTH = 300;
    int DESIRED_HEIGHT = 600;

    // Locators for UI elements
    By getCoins = new By.ById("com.spbtv.mobilinktv:id/iv_coin_top");
    By CapCoins = new By.ById("com.spbtv.mobilinktv:id/tv_coins");
    By getLevel = new By.ById("com.spbtv.mobilinktv:id/tv_level");
    By redeemClick = new By.ById("com.spbtv.mobilinktv:id/iv_redeem");
    By coinsCheck = new By.ById("com.spbtv.mobilinktv:id/tv_coins_value");
    By clickMB = new By.ByXPath("//android.widget.TextView[@resource-id=\"com.spbtv.mobilinktv:id/tv_pkr_value\" and @text=\"Daily Package\"]");
    By finalRedeem = new By.ById("com.spbtv.mobilinktv:id/tv_Redeem");
    By popUpVerification = new By.ById("com.spbtv.mobilinktv:id/tv_text");
    By Okay = new By.ById("com.spbtv.mobilinktv:id/tv_ok");
    By alreadySubscribedMessage = new By.ByXPath("//android.widget.TextView[@resource-id=\"android:id/message\"]");
    By needSupportLaterclick = new By.ByXPath("com.spbtv.mobilinktv:id/tv_");
    By clickOkayPopup = new By.ById("android:id/button2");

    /**
     * Method to get count, process redeem, and restart the app.
     *
     * @param driver The AppiumDriver instance.
     * @throws InterruptedException If interrupted while waiting.
     * @throws IOException          If an I/O error occurs.
     */
    public void getCount(AppiumDriver<MobileElement> driver) throws InterruptedException, IOException {
        getLevel(driver);
        redeemFunction(driver);
        restartApp(driver);
    }

    /**
     * Method to restart the application.
     *
     * @param driver The AppiumDriver instance.
     */
    private void restartApp(AppiumDriver<MobileElement> driver) {
        driver.closeApp();
        driver.launchApp();
    }

    /**
     * Method to retrieve the user's level.
     *
     * @param driver The AppiumDriver instance.
     */
    private void getLevel(AppiumDriver<MobileElement> driver) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.presenceOfElementLocated(getCoins)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(getLevel));
        MobileElement levelNo = driver.findElement(getLevel);
        String level = levelNo.getText();
        System.out.println("This user is currently on : " + level);
        Reporter.log("<p>This user is currently on : </p>" + level);
    }

    /**
     * Method to process the redeem action.
     *
     * @param driver The AppiumDriver instance.
     * @throws InterruptedException If interrupted while waiting.
     * @throws IOException          If an I/O error occurs.
     */
    private void redeemFunction(AppiumDriver<MobileElement> driver) throws InterruptedException, IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(ExpectedConditions.presenceOfElementLocated(redeemClick)).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(coinsCheck));
            MobileElement totalCoinsElement = driver.findElement(coinsCheck);
            int totalCoins = Integer.parseInt(totalCoinsElement.getText());
            System.out.println("Your Total Coins are: " + totalCoins);
            Reporter.log("<p>Your Total Coins are: " + totalCoins + "</p>");
            Thread.sleep(5000);

            // Click on the element to trigger the check
            MobileElement otherElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(clickMB));
            otherElement.click();

            // Check for the "already subscribed" message
            MobileElement checkIfAlreadySubscribed = null;

            // Determine subscription status and handle accordingly
            if (totalCoins > 80) {
                // Proceed if not subscribed
                MobileElement redeemButton = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(finalRedeem));
                System.out.println("The user is eligible for the package");
                Reporter.log("<p>The user is eligible for the package</p>");
                redeemButton.click();
                verifyRedeem(driver); // Verify redemption process


            } else {
                checkIfAlreadySubscribed = driver.findElement(alreadySubscribedMessage);
                assert checkIfAlreadySubscribed != null;
                if (checkIfAlreadySubscribed.isDisplayed()){

                    String alreadySubText = checkIfAlreadySubscribed.getText();
                    System.out.println("Status is: " + alreadySubText);
                    Reporter.log("<p>Status is: " + alreadySubText + "</p>");

                }
            }

        } catch (NoSuchElementException e) {
            System.out.println("Element not found exception: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("IO Exception during redemption: ", e);
        }
    }


    /**
     * Method to verify the redeem action.
     *
     * @param driver The AppiumDriver instance.
     * @throws InterruptedException If interrupted while waiting.
     * @throws IOException          If an I/O error occurs.
     */
    private void verifyRedeem(AppiumDriver<MobileElement> driver) throws InterruptedException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.presenceOfElementLocated(popUpVerification));
        MobileElement verifymessage = driver.findElement(popUpVerification);
        String popUp = verifymessage.getText();

        if (popUp.contains("Mubarak ho")) {
            System.out.println("You have been successfully give a 100 Ruppee Balance on your Registered Number");
            Reporter.log("<p>You have been successfully give a 100 Ruppee Balance on your Registered Number</p>");
            takeScreenshot(driver);
            MobileElement clickOkay = driver.findElement(Okay);
            clickOkay.click();
        } else if (popUp.contains("Ma'azrat")) {
            System.out.println("You are unable to Subscribe to the Package");
            Reporter.log("<p>You are unable to Subscribe to the Package</p>");
            takeScreenshot(driver);
            MobileElement clickOkay = driver.findElement(Okay);
            clickOkay.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(needSupportLaterclick)).click();
        }
    }

    /**
     * Method to take a screenshot.
     *
     * @param driver The AppiumDriver instance.
     * @throws IOException If an I/O error occurs.
     */
    private void takeScreenshot(AppiumDriver<MobileElement> driver) throws IOException {
        File screenshotFile = driver.getScreenshotAs(OutputType.FILE);
        byte[] screenshotBytes = Files.readAllBytes(screenshotFile.toPath());
        String screenshotBase64 = Base64.getEncoder().encodeToString(screenshotBytes);

        Reporter.log("<h4>Here is the Screen Shot</h4>");
        Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + DESIRED_WIDTH + "\" height=\"" + DESIRED_HEIGHT + "\" ></p>");
    }
}
