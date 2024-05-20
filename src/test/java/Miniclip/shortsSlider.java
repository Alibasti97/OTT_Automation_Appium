package Miniclip;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

public class shortsSlider {

    public void swipeShorts(AppiumDriver<MobileElement> driver) throws InterruptedException{

        // Open the Shorts tab
        openShortsTab(driver);
        // Swipe up to the next short/reel video
        swipeUpWithPause(driver);
    }

    private static void openShortsTab(AppiumDriver<MobileElement> driver) {
        // Identify and click on the Shorts tab
        MobileElement shortsTab = driver.findElement(By.xpath(""));
        shortsTab.click();
    }

    private static void swipeUpWithPause(AppiumDriver<MobileElement> driver) throws InterruptedException {
        // Perform the first swipe action to scroll to the next video
        int durationInSeconds = 5;
        System.out.println("Performing the first swipe up...");
        swipeUpFromMiddle(driver);

        // Wait for the specified duration
        Thread.sleep(durationInSeconds * 1000);

        // Perform the second swipe action after the pause
        System.out.println("Performing the second swipe up...");
        swipeUpFromMiddle(driver);
        // Wait for the specified duration
        Thread.sleep(durationInSeconds * 1000);
        // Perform the second swipe action after the pause
        System.out.println("Performing the third swipe up...");
        swipeUpFromMiddle(driver);
    }

    private static void swipeUpFromMiddle(AppiumDriver<MobileElement> driver) {
        Dimension size = driver.manage().window().getSize();
        int middleY = size.height / 2;
        int startY = middleY;
        int endY = (int) (size.height * 0.2);
        int startX = size.width / 2;

        String swipeCommand = String.format("adb shell input swipe %d %d %d %d", startX, startY, startX, endY);
        driver.executeScript("mobile:shell", ImmutableMap.of("command", swipeCommand));
    }
}
