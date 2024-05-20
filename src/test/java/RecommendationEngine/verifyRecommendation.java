package RecommendationEngine;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Base64;
import java.util.NoSuchElementException;

public class verifyRecommendation {

    public static void verifyREmain(AppiumDriver<MobileElement> driver) throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, 100);
        openLiveTile(driver);
        MobileElement elementToSwipe = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.widget.ImageView[@resource-id=\"com.spbtv.mobilinktv:id/imageView\"])[17]"))); // Update the ID for your related channels
//        System.out.println("element appeared: " + elementToSwipe.isDisplayed());
        takescreenshot(driver, "Screenshot_BEFORE_swipe");
        for(int i = 0; i <= 15; i++){
            swipeElementVertically(driver, elementToSwipe);
        }
        Thread.sleep(5000);
        takescreenshot(driver, "Screenshot_AFTER_swipe");

    }
    private static void openLiveTile(AppiumDriver<MobileElement> driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 100); // Initializing WebDriverWait
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.spbtv.mobilinktv:id/relativeLayout\"])[1]"))).click(); // Clicking on TenSports tile
        Thread.sleep(5000);
    }

    public static void swipeElementVertically(AppiumDriver<MobileElement> driver, MobileElement elementToSwipe) {
        int elementStartX = elementToSwipe.getLocation().getX();
        int elementStartY = elementToSwipe.getLocation().getY();
        int elementWidth =  elementToSwipe.getSize().getWidth();
        int elementHeight = elementToSwipe.getSize().getHeight();

        int startX = elementStartX + elementWidth + 40;
        int endX = elementStartX + 60;
        int centerY = elementStartY + elementHeight / 2;

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(startX, centerY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(endX, startX))
                .release()
                .perform();
    }

    private static void takescreenshot(AppiumDriver<MobileElement> driver, String screenshotName) {
        final int DESIRED_WIDTH = 300;
        final int DESIRED_HEIGHT = 600;
        String screenshotBase64 = "";
        try {
            File screenshotFile = driver.getScreenshotAs(OutputType.FILE);
            byte[] screenshotBytes = Files.readAllBytes(screenshotFile.toPath());
            screenshotBase64 = Base64.getEncoder().encodeToString(screenshotBytes);

        } catch (IOException | NoSuchElementException | TimeoutException ex) {
            ex.printStackTrace();

        }

        Reporter.log("<h4>Here is the Screen Shot</h4>");
        Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + DESIRED_WIDTH + "\" height=\"" + DESIRED_HEIGHT + "\" ></p>");
    }

}
