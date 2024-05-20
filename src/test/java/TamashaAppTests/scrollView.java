package TamashaAppTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.NoSuchElementException;

public class scrollView {


    public void screenScrolling(AppiumDriver<MobileElement> driver) throws InterruptedException {
        int desiredWidth = 300; // Set your desired width of SS
        int desiredHeight = 600; // Set your desired height of SS
        int scrollCount = 1;
        for (int i = 0; i < scrollCount; i++) {
            String screenshotBase64 = null;
            try {
                scrollToBottom(driver);
                scrollToTop(driver);

//                System.out.println("Completed scroll iteration: " + (i + 1));
//                Reporter.log("<b><p>Completed scroll iteration: </p></b>" + (i + 1));

                Thread.sleep(1000);


            } catch (InterruptedException | WebDriverException | NoSuchElementException excp) {
                Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + desiredWidth + "\" height=\"" + desiredHeight + "\" ></p>");
                Reporter.log("<b><p>app Stuck message: </p></b>" + excp.getMessage());
                resetApp(driver);
            }
        }
        resetApp(driver);
    }

    private void scrollToBottom(AppiumDriver<MobileElement> driver) {
        WebElement bottomElement = driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollToEnd(10)"));

        int bottomY = bottomElement.getLocation().getY();
        int screenHeight = driver.manage().window().getSize().getHeight();

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(0, bottomY)).moveTo(PointOption.point(0, screenHeight / 2))
                .release()
                .perform();
    }

    private void scrollToTop(AppiumDriver<MobileElement> driver) {
        WebElement topElement = driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollToBeginning(10)"));

        int topY = topElement.getLocation().getY();
        int screenHeight = driver.manage().window().getSize().getHeight();

        TouchAction touchAction = new TouchAction(driver);
        touchAction
                .press(PointOption.point(0, screenHeight / 2))
                .moveTo(PointOption.point(0, topY))
                .release()
                .perform();
    }

    private static void resetApp(AppiumDriver<MobileElement> driver){
        driver.closeApp();
        driver.launchApp();
    }


}