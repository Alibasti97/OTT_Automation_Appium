package EditProfile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Login.loginFlow;

public class deleteUser {

    static By clickDeleteBtn = new By.ById("com.spbtv.mobilinktv:id/tv_delete");
    static By ConfirmDeletionBtn = new By.ById("android:id/button1");

    public static void DeleteUserandSignInAgain(AppiumDriver<MobileElement> driver){

        profileEdit.clickMenu(driver);
        profileEdit.clickProfileIcon(driver);
        profileEdit.clickEditBtn(driver);
        clickDelete(driver);
        confirmDelete(driver);
        restartApp(driver);
        loginFlow.login(driver);


    }

    public static void clickDelete(AppiumDriver<MobileElement> driver){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(clickDeleteBtn)).click();
    }

    public static void confirmDelete(AppiumDriver<MobileElement> driver){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(ConfirmDeletionBtn)).click();

    }

    public static void restartApp(AppiumDriver<MobileElement> driver){

        driver.closeApp();
        driver.launchApp();

    }


}
