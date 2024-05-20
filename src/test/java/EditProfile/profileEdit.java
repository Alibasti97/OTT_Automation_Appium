package EditProfile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import EditProfile.randomString;
import java.util.List;
import java.util.Random;

import static EditProfile.randomString.generateRandomString;

public class profileEdit {

    private static final String randomName = generateRandomString(10); // Change the length as needed

    private static final By namefieldID = new By.ById("com.spbtv.mobilinktv:id/et_full_name");

    public void profileEdit(AppiumDriver<MobileElement> driver) throws InterruptedException {

        clickMenu(driver);
        clickProfileIcon(driver);
        clickEditBtn(driver);
        clearName(driver);
        enternewName(driver);


    }

  public static void clickMenu(AppiumDriver<MobileElement> driver) {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        // Click Menu Bar
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.spbtv.mobilinktv:id/tv_item_5"))).click();
        // Click Profile Pic to go to Profile Section

    }

    public static void clickProfileIcon(AppiumDriver<MobileElement> driver) {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.RelativeLayout[@resource-id=\"com.spbtv.mobilinktv:id/lyProfilePic\"]/android.widget.ImageView[2]"))).click();

    }

    public static void clickEditBtn(AppiumDriver<MobileElement> driver) {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/tv_edit"))).click();
    }

    private static void takeScreenshot(AppiumDriver<MobileElement> driver) throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] screenshotBytes = Files.readAllBytes(screenshotFile.toPath());
        String screenshotBase64 = Base64.getEncoder().encodeToString(screenshotBytes);

        Reporter.log("<h4>Here is the Screen Shot</h4>");
        // Set your desired width of SS
        int desiredWidth = 300;
        // Set your desired height of SS
        int desiredHeight = 600;
        Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + desiredWidth + "\" height=\"" + desiredHeight + "\" ></p>");
    }

    private static void clearName(AppiumDriver<MobileElement> driver){


        WebDriverWait wait = new WebDriverWait(driver, 50);
        MobileElement clearname = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(namefieldID));
        clearname.clear();

    }
    private static void enternewName(AppiumDriver<MobileElement> driver){


        WebDriverWait wait = new WebDriverWait(driver, 50);
        MobileElement enterName = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(namefieldID));
        System.out.println("Generated random name: " + randomName);
        enterName.sendKeys(randomName);

    }

}


