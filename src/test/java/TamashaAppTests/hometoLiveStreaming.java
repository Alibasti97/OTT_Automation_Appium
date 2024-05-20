package TamashaAppTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.lang3.time.StopWatch;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class hometoLiveStreaming {


    public void liveStreaming(AppiumDriver<MobileElement> driver) throws InterruptedException {
        performLiveStreamingTest(driver);
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

    private static void launchLiveStreaming(AppiumDriver<MobileElement> driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.spbtv.mobilinktv:id/relativeLayout\"])[1]")))
                .click();
    }

    private static void waitForLiveStreaming(AppiumDriver<MobileElement> driver) {
        WebDriverWait wait = new WebDriverWait(driver, 140);
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.spbtv.mobilinktv:id/ly_main\"])[2]")));
    }

    private static void resetApp(AppiumDriver<MobileElement> driver) throws InterruptedException {
        Thread.sleep(3000);
        driver.closeApp();
        driver.launchApp();
    }


    public static void performLiveStreamingTest(AppiumDriver<MobileElement> driver) throws InterruptedException {


        int loopDurationMinutes = 1;
        int loopIntervalSeconds = 15;
        int desiredWidth = 300;
        int desiredHeight = 600;


        for (int i = 0; i < loopDurationMinutes * 60 / loopIntervalSeconds; i++) {
            String screenshotBase64 = null;
            try {
                LocalDateTime currentDateTime = LocalDateTime.now();

                launchLiveStreaming(driver);

                StopWatch loadTime = new StopWatch();
                loadTime.start();

                waitForLiveStreaming(driver);

                loadTime.stop();
                long pageLoadTime_ms = loadTime.getTime();

                System.out.println("Load Time from Home to Live Streaming is: " + pageLoadTime_ms + " milliseconds, " + currentDateTime);

                screenshotBase64 = takeScreenshotAsBase64(driver);

                Reporter.log("<h3>Iteration " + (i + 1) + " Ended</h3>");
                Reporter.log("<p>Load Time from Home to Live Streaming is: " + pageLoadTime_ms + " milliseconds, " + currentDateTime + "</p>");
                Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + desiredWidth + "\" height=\"" + desiredHeight + "\" ></p>");

                resetApp(driver);
            } catch (TimeoutException | NoSuchElementException | ElementNotVisibleException |
                     NoSuchSessionException excp) {
                Reporter.log("<b>app Stuck message:</b>" + excp.getMessage());
                Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + desiredWidth + "\" height=\"" + desiredHeight + "\" ></p>");
                Thread.sleep(3000);
                System.out.println("Cause:" + excp.getCause());
                resetApp(driver);
            } finally {
                System.out.println("Iteration " + (i + 1) + " Ended");
            }
        }
    }



}