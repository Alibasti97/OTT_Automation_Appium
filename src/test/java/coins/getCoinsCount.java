package coins;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class getCoinsCount {
    By clickMore = new By.ById("com.spbtv.mobilinktv:id/item_profile_home");
    By getCoins = new By.ById("com.spbtv.mobilinktv:id/tv_coins");
    By coinsPKRvalue = new By.ById("com.spbtv.mobilinktv:id/tv_pkr_value");
    By getSubscribedPackage = new By.ById("com.spbtv.mobilinktv:id/tv_sub_status");

    public void printTotalCoins(AppiumDriver<MobileElement> driver)throws InterruptedException{

        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.presenceOfElementLocated(clickMore)).click();

        Thread.sleep(5000);

       MobileElement getCoinsCount = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(getCoins));
       String coinsCount = getCoinsCount.getText();
       System.out.println("Current Coins Count is: " + coinsCount);
       Reporter.log("Current Coins Count is: " + coinsCount);

        MobileElement getCoinsPKRvalue = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(coinsPKRvalue));
        String pkrValue = getCoinsPKRvalue.getText();
        System.out.println("Current PKR Value is: " + pkrValue);
        Reporter.log("Current PKR Value is: " + pkrValue);
        getSubscribepackagename(driver);
        driver.navigate().back();

    }

    private void getSubscribepackagename(AppiumDriver<MobileElement> driver)throws InterruptedException{

        WebDriverWait wait = new WebDriverWait(driver, 10);
        String packageSubscribed = wait.until(ExpectedConditions.presenceOfElementLocated(getSubscribedPackage)).getText();

        System.out.println("Your Current Package is: " + packageSubscribed);
        Reporter.log("Your Current Package is: " + packageSubscribed);


    }
}
