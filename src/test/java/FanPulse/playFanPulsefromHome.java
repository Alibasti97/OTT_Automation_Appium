package FanPulse;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import static java.lang.StringTemplate.STR;

public class playFanPulsefromHome {


    public static void playFanPulse(AppiumDriver<MobileElement> driver) throws IOException, InterruptedException {
        clickFanPulseIcon(driver);
        readTeamNameandPrint(driver);
        clickStartButton(driver);
        verifyifthereisaquiztoPlay(driver);

    }

    private static void clickFanPulseIcon(AppiumDriver<MobileElement> driver){

        WebDriverWait wait = new WebDriverWait(driver, 100);
        MobileElement fanPulseicon = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.spbtv.mobilinktv:id/iv_quiz_result")));
        fanPulseicon.click();

    }

    private static void readTeamNameandPrint(AppiumDriver<MobileElement> driver){
        WebDriverWait wait = new WebDriverWait(driver, 100);
        MobileElement teamName = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Jazz Laals\"]")));
        String nameofSelectedTeam = teamName.getText();
        System.out.println(nameofSelectedTeam);
        Reporter.log("Current Selected Team is:" + nameofSelectedTeam);
    }


    private static void clickStartButton(AppiumDriver<MobileElement> driver){

        WebDriverWait wait = new WebDriverWait(driver, 100);
        MobileElement clickStartButtontoStartQuiz = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@text=\"Start\"]")));
        clickStartButtontoStartQuiz.click();

    }

    private static void verifyifthereisaquiztoPlay(AppiumDriver<MobileElement> driver) throws InterruptedException, IOException {

            Thread.sleep(5000); // Adding a short delay
            WebDriverWait wait = new WebDriverWait(driver, 100);
            // Find the first choice element using XPath
            WebElement firstChoice = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@resource-id='0']")));
        // Check if the first choice exists in the DOM
        if (firstChoice != null) {

            // Click on the first choice
            firstChoice.click();
            Thread.sleep(5000);
            Reporter.log("<b>Answered the Question</b>");
            takeScreenshot(driver);
            clickNextAfterAnswering(driver);
            clickGoToLeaderBoard(driver);
            takeScreenshot(driver);

        }else {

            // If the first choice doesn't exist, log and handle accordingly
            getTextifQuestionNotAvailable(driver);
            takeScreenshot(driver);
        }


    }

private static void getTextifQuestionNotAvailable(AppiumDriver<MobileElement> driver){
    WebDriverWait wait = new WebDriverWait(driver, 20);
    MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Filhal Question live nahi hai. Thori dair me dubara check karen.\"]")));
    String elementText = element.getText();
    System.out.println(elementText);
    Reporter.log("<p>" + elementText + "</p>");

}

    private static void clickGoToLeaderBoard(AppiumDriver<MobileElement> driver){

        // Close the Quiz window
        WebDriverWait wait = new WebDriverWait(driver, 100); // Initializing WebDriverWait
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("leaderboard-button"))).click();

    }

    private static void restartApp(AppiumDriver<MobileElement> driver){

    driver.closeApp();
    driver.launchApp();

    }

private static void clickNextAfterAnswering(AppiumDriver<MobileElement> driver){
    // Clicking the Next Button After Answering the Question
    WebDriverWait wait = new WebDriverWait(driver, 20); // Initializing WebDriverWait
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@text=\"Next\"]"))).click();
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


}

