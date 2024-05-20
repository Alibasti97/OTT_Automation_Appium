package coins;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class loadTimeMyRewards {
    By MoreIcon = new By.ById("com.spbtv.mobilinktv:id/item_profile_home");
    By MyRewardsBtn = new By.ByXPath("(//android.widget.RelativeLayout[@resource-id=\"com.spbtv.mobilinktv:id/relativeLayout\"])[3]");
    By crownAppears = new By.ById("com.spbtv.mobilinktv:id/iv_crown");

    private static final int LOOP_DURATION_MINUTES = 1; // Set Test Time in Minutes
    private static final int LOOP_INTERVAL_SECONDS = 15; // Time between each iteration (seconds)



   public void rewardsScreenLoadTime(AppiumDriver<MobileElement> driver) {
       int iterationCount = 0;

       for (int i = 0; i < LOOP_DURATION_MINUTES * 60 / LOOP_INTERVAL_SECONDS; i++) {
           clickMoreIcon(driver);
           clickMyRewards(driver);

           calculateLoadTime(driver);
       }
       iterationCount++;
   }

    private void clickMoreIcon(AppiumDriver<MobileElement> driver) {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        MobileElement clickMore = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(MoreIcon));
        clickMore.click();

    }

    private void clickMyRewards(AppiumDriver<MobileElement> driver) {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        MobileElement clickMyRewardsBtn = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(MyRewardsBtn));
        clickMyRewardsBtn.click();

    }

    private void calculateLoadTime(AppiumDriver<MobileElement> driver) {

        WebDriverWait wait = new WebDriverWait(driver, 50);

            try {
                StopWatch loadTime = new StopWatch();
                LocalDateTime currentDateTime = LocalDateTime.now();
                loadTime.start();
                wait.until(ExpectedConditions.presenceOfElementLocated(crownAppears));
                loadTime.stop();
                double loadTimeMillis = loadTime.getTime() / 1000.0;
                System.out.println("Load Time from More to Redeem Screen is: " + loadTimeMillis + " Seconds | " + currentDateTime);
                TimeUnit.SECONDS.sleep(LOOP_INTERVAL_SECONDS);
                driver.closeApp();
                driver.launchApp();
            } catch (InterruptedException | TimeoutException e) {
                System.out.println(e.getMessage());
            }

        }

    }

