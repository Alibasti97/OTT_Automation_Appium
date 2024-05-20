package search;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.NoSuchElementException;

public class SearchBar {

    private final int DESIRED_WIDTH = 300;
    private final int DESIRED_HEIGHT = 600;

    public void searchFunctionality(AppiumDriver<MobileElement> driver) throws IOException, InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            clickSearchIcon(wait);
            enterSearchKeyword(wait, "geo");
            initiateSearch(driver);
            checkResults(wait);
            clickFirstResult(wait);
            verifyStreaming(wait);
            takeScreenshotAndResetApp(driver);
            resetApp(driver);
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println(e.getMessage());
            Reporter.log(e.getMessage());
            resetApp(driver);

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            Reporter.log(e.getMessage());
            resetApp(driver);
        }
    }

    private void clickSearchIcon(WebDriverWait wait) {
        MobileElement searchIcon = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/iv_search")));
        searchIcon.click();
    }

    private void resetApp(AppiumDriver<MobileElement> driver) {
        driver.closeApp();
        driver.launchApp();

    }

    private void enterSearchKeyword(WebDriverWait wait, String keyword) {
        MobileElement searchField = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.spbtv.mobilinktv:id/et_search")));
        searchField.sendKeys(keyword);
    }

    private void initiateSearch(AppiumDriver<MobileElement> driver) {
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
    }

    private void checkResults(WebDriverWait wait) {
        MobileElement resultsList = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.spbtv.mobilinktv:id/rv_search_all_channel")));

        if (resultsList.isDisplayed()){
            System.out.println("Results Displayed Successfully");
            Reporter.log("<h4><p>Results Displayed successfully</p></h4>");
        }else {
            assertElementDisplayed(resultsList, "Results not displayed");
            Reporter.log("<h4><p>Results not Displayed</p></h4>");
            System.out.println("Results not Displayed");
        }
    }

    private void clickFirstResult(WebDriverWait wait) {
        MobileElement clickFirstResult = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//android.widget.ImageView[@resource-id=\"com.spbtv.mobilinktv:id/imageView\"])[1]")));
        clickFirstResult.click();
    }

    private void verifyStreaming(WebDriverWait wait) {
        MobileElement verifyFirstResult = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.spbtv.mobilinktv:id/tv_name")));
        assertElementDisplayed(verifyFirstResult, "Geo news not streaming");

        System.out.println("Geo news Started Streaming Successfully");
        Reporter.log("<b>Geo news Started Streaming Successfully</b>");
    }

    private void takeScreenshotAndResetApp(AppiumDriver<MobileElement> driver) throws IOException {
        File screenshotFile = driver.getScreenshotAs(OutputType.FILE);
        byte[] screenshotBytes = Files.readAllBytes(screenshotFile.toPath());
        String screenshotBase64 = Base64.getEncoder().encodeToString(screenshotBytes);

        Reporter.log("<h4>Here is the Screen Shot</h4>");
        Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"" + DESIRED_WIDTH + "\" height=\"" + DESIRED_HEIGHT + "\" ></p>");

    }

    private void assertElementDisplayed(MobileElement element, String message) {
        Assert.assertTrue(element.isDisplayed(), message);
    }

    private void handleException(Exception e, AppiumDriver<MobileElement> driver) {
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
