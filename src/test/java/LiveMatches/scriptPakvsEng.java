package LiveMatches;

//import DynamicTile.dTile;
import DynamicTile.dTile;
import TamashaAppTests.liveTV;
import VOD.VODfunctionality;
import comments.verifyLiveStreaming;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import TamashaAppTests.splashTohome;
import Login.loginFlow;
import comments.CommentAutomation;
import FanPulse.answertheQuestion;
import org.testng.Reporter;

import java.io.IOException;

public class scriptPakvsEng {
    AppiumDriver<MobileElement> driver;
    public static void pakvseng(AppiumDriver<MobileElement> driver) throws InterruptedException {

        Reporter.log("<table border='1'>");
        Reporter.log("<tr><th bgcolor='#FFFF00'>Device Name</th><th bgcolor='#FFFF00'>Android Version</th><th bgcolor='#FFFF00'>Ram/Storage</th><th bgcolor='#FFFF00'>ISP Name</th></tr>");
        Reporter.log("<tr><td>Redmi 12C</td><td>14</td><td>4/64GB</td><td>PTCL</td></tr>");
        Reporter.log("</table>");

        // Run the script for 180 minutes (180 iterations)
        for (int i = 0; i < 1; i++) {
            try {
                System.out.println("Iteration " + (i+1) + " Started");
                Reporter.log("<h1>Iteration " + (i+1) + " Started</h1>");
                Reporter.log("<h1>Test 1: Calculate Load Time from Splash To Home Screen</h1>");
                splashTomain(driver);
//                Reporter.log("<h1>Test 2: Dynamic Tile Verification</h1>");
//                DynamicTile(driver);
//                Reporter.log("<h1>Test 3: Login Flow</h1>");
//                loginNow(driver);
                Reporter.log("<h1>Test 2: VOD Flow</h1>");
                Vod(driver);

//                Reporter.log("<h1>Test 3: Comments Flow</h1>");
//                Reporter.log("Login Flow is integrated with the Comments Flow");
//                postingComments(driver);
//                Reporter.log("<h1>Test 4: Fan Pulse Flow</h1>");
//                fanPulse(driver);
                Reporter.log("<h1>Test 3: Live TV Flow</h1>");
                liveTVVerify(driver);

                Reporter.log("<h1>Test 4: Live Match Streaming Verification</h1>");
                verifyLiveStreaming(driver);

                System.out.println("Iteration " + (i+1) + " Ended");
                Reporter.log("<h1>Iteration " + (i+1) + " Ended</h1>");
                // Wait for 1 minute
                Thread.sleep(30000); //  = 40 seconds wait before going for another Iteration
                restartApp(driver);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void splashTomain(AppiumDriver<MobileElement> driver) throws InterruptedException {

        splashTohome.splashScrToHome(driver);
    }

    public void loginNow(AppiumDriver<MobileElement> driver) throws InterruptedException {

        loginFlow.login(driver);

    }

    public static void verifyLiveStreaming(AppiumDriver<MobileElement> driver){
        verifyLiveStreaming.verifyLiveStreaming(driver);


    }
    public static void postingComments(AppiumDriver<MobileElement> driver) throws InterruptedException{

        CommentAutomation newComments = new CommentAutomation();
        CommentAutomation.postComments(driver);

    }

    public static void fanPulse(AppiumDriver<MobileElement> driver) throws InterruptedException{

        answertheQuestion newFanPulse = new answertheQuestion();
        answertheQuestion.automateFanpulse(driver);

    }

    public static void liveTVVerify(AppiumDriver<MobileElement> driver) throws InterruptedException, IOException {

        liveTV liveStr = new liveTV();
        liveStr.switchToLiveTV(driver);

    }

    public void DynamicTile(AppiumDriver<MobileElement> driver) throws InterruptedException{

        DynamicTile.dTile Dtile = new dTile();
        dTile.VerifyTile(driver);

    }

    public static void Vod(AppiumDriver<MobileElement> driver) throws InterruptedException{

        VOD.VODfunctionality VideoOnDemand = new VODfunctionality();
        VODfunctionality.mainVOD(driver);

    }

    public static void restartApp(AppiumDriver<MobileElement> driver) throws InterruptedException{

        driver.closeApp();
        driver.launchApp();

    }

}
