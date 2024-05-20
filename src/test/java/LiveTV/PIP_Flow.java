package LiveTV;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.NoSuchElementException;

public class PIP_Flow {

    By pictureinpicture= new By.ByXPath("(//android.widget.RelativeLayout[@resource-id=\"com.spbtv.mobilinktv:id/relativeLayout\"])[2]");
    By Clickpipiconbtn= new By.ById("com.spbtv.mobilinktv:id/pip");
    public void pipmethod(AppiumDriver<MobileElement> driver) throws InterruptedException {
        ClickliveStramingtile(driver);
        Pipicon(driver);
        Thread.sleep(10000);
        takescreenshoot(driver);
        Exitapp(driver);
    }
    private void ClickliveStramingtile(AppiumDriver<MobileElement> driver){
        WebDriverWait wait= new WebDriverWait(driver,20);
        MobileElement Clickonlivechannal= (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(pictureinpicture));
        Clickonlivechannal.click();
    }
    private void Pipicon(AppiumDriver<MobileElement> driver){
        WebDriverWait wait= new WebDriverWait(driver,20);
        MobileElement Clickonpipicon = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(Clickpipiconbtn));
        Clickonpipicon.click();
    }

    private void takescreenshoot(AppiumDriver<MobileElement> driver) {
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
    private void Exitapp(AppiumDriver<MobileElement>driver)throws InterruptedException {
        driver.navigate().back();
        Thread.sleep(5000);
        takescreenshoot(driver);
    }
}
