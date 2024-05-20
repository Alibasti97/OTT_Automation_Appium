package TamashaAppTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;

public class viewMoreSS {

    public static void scrollAndSS(AppiumDriver<MobileElement> driver) throws InterruptedException {

        waitForclick(driver);
        Thread.sleep(5000);
        // Take initial screenshot
        String screenshotBase64 = takeScreenshotAsBase64(driver);
        embedScreenshotInReport(screenshotBase64);

        // Perform 12 scrolls with screenshots
        for (int i = 0; i < 2; i++) {
            scrollToBottomWithWaits(driver);

            Thread.sleep(5000);
            // Take screenshot
            screenshotBase64 = takeScreenshotAsBase64(driver);
            embedScreenshotInReport(screenshotBase64);
        }
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


//    private static void scrollToBottom(AppiumDriver<MobileElement> driver) {
//        WebElement bottomElement = driver.findElement(MobileBy.AndroidUIAutomator(
//                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollToEnd(1)"));
//
//        int bottomY = bottomElement.getLocation().getY();
//        int screenHeight = driver.manage().window().getSize().getHeight();
//
//        TouchAction touchAction = new TouchAction(driver);
//        touchAction.press(PointOption.point(0, bottomY)).moveTo(PointOption.point(0, screenHeight / 2))
//                .release()
//                .perform();
//    }

    private static void scrollToBottomWithWaits(AppiumDriver<MobileElement> driver) {
            try {
                // Scroll using UiScrollable first
                driver.findElement(MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(1)")); // Adjust timeout if needed
            } catch (Exception e) {
                // Fallback to TouchAction if UiScrollable fails
                int screenHeight = driver.manage().window().getSize().getHeight();
                int startY = screenHeight * 4 / 5; // Start slightly above the bottom
                int endY = screenHeight / 5; // Scroll to near the top
                new TouchAction(driver).press(PointOption.point(0, startY))
                        .moveTo(PointOption.point(0, endY)).release().perform();
            }

            // Wait for 5 seconds after the scroll
            try {
                Thread.sleep(5000); // Replace with a more robust wait mechanism if needed
            } catch (InterruptedException e) {
                // Handle interruption if necessary
            }
        }


    private static void waitForclick(AppiumDriver<MobileElement> driver)throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 140);
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("(//android.widget.TextView[@text=\"View More\"])[2]")))
                .click();
    }
    private static void embedScreenshotInReport(String screenshotBase64) {
        Reporter.log("<br><img src='data:image/png;base64, " + screenshotBase64 + "' height='600' width='300'/><br>");
    }

}
