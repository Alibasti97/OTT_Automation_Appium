package FanPulse;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class fanPulseLeaderboardLastUpdate {

     private static final By jazzIcon = new By.ById("com.spbtv.mobilinktv:id/iv_quiz_result");

     private static final By leaderboardLink = new By.ByXPath("//android.widget.TextView[@text=\"  Leaderboard\"]");
    private static final By lastUpdate = new By.ByXPath("(//android.widget.Image[@text=\"info\"])[3]");

    public static void mainMethod (AppiumDriver<MobileElement> driver){

        clickingJazzIcon(driver);
        clickingLeaderboardLink(driver);
        clickingLastUpdateInfoIcon(driver);
    }

    public static void clickingJazzIcon(AppiumDriver<MobileElement> driver){

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(jazzIcon)).click();

    }

    private static void  clickingLeaderboardLink(AppiumDriver<MobileElement> driver){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(leaderboardLink)).click();

    }

    private static void  clickingLastUpdateInfoIcon(AppiumDriver<MobileElement> driver){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(lastUpdate)).click();

    }


}
