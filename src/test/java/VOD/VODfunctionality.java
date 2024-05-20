package VOD;

import HandlingLobby.handleLobby;
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

public class VODfunctionality {

    private static final int desiredWidth = 300; // Desired width for screenshot
    private static final int desiredHeight = 600; // Desired height for screenshot
    public static void mainVOD(AppiumDriver<MobileElement> driver) throws InterruptedException {
        Thread.sleep(10000);
        if (handleLobby.checkAndHandleLobby(driver)) {
            System.out.println("Lobby was present and handled.");
        } else {
            System.out.println("No lobby to handle or lobby handling complete.");
        }
        swipeDown(driver);
        clickonVODTile(driver);
        Reporter.log("<b>VOD Started</b>");
        String screenshotBase64 = null; // Initializing screenshotBase64 variable
        screenshotBase64 = takeScreenshotAsBase64(driver); // Method call to take screenshot
        Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + desiredWidth + "\" height=\"" + desiredHeight + "\" ></p>"); // Logging screenshot
        driver.navigate().back();

    }

    private static void clickTenSportsTile(AppiumDriver<MobileElement> driver){
        WebDriverWait wait = new WebDriverWait(driver, 100); // Initializing WebDriverWait
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.spbtv.mobilinktv:id/relativeLayout\"])[2]"))).isDisplayed(); // Clicking on TenSports tile
    }

    private static void swipeDown(AppiumDriver<MobileElement> driver){
        clickTenSportsTile(driver);
        WebElement bottomElement = driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollToEnd(7)"));

        int bottomY = bottomElement.getLocation().getY();
        int screenHeight = driver.manage().window().getSize().getHeight();

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(0, bottomY)).moveTo(PointOption.point(0, screenHeight / 2))
                .release()
                .perform();

    }

    private static void clickonVODTile(AppiumDriver<MobileElement> driver) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.spbtv.mobilinktv:id/relativeLayout\"])[11]"))).click();
        waitfor10Sec(driver);
    }

    private static void waitfor10Sec(AppiumDriver<MobileElement> driver)throws InterruptedException{
        Thread.sleep(20000);

    }

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
