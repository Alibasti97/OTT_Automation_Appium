package TamashaAppTests;


import DynamicTile.dTile;
import EditProfile.deleteUser;
import FanPulse.answertheQuestion;
import FanPulse.fanPulseHowitWorks;
import FanPulse.playFanPulsefromHome;
import LiveMatches.scriptPakvsEng;
import LiveTV.PIP_Flow;
import coins.coinsRedeem;
import coins.loadTimeMyRewards;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import LiveMatches.scriptPakvsEng;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import search.SearchBar;
import Login.loginFlow;
import coins.getCoinsCount;
import comments.CommentAutomation;
import comments.verifyLiveStreaming;
import Miniclip.reelsMiniclip;
import VOD.VODfunctionality;
import EditProfile.profileEdit;
import RecommendationEngine.verifyRecommendation;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class mainApp {
    AppiumDriver<MobileElement> driver;

    public void main(String[] args) {

        try {
            openTamasha();
            screenVer();
            liveStreaming();
            splashToHomeScreen();
            HometoStreaming();
            slider();
            search();
            tearDown();

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }

//        mainApp app = new mainApp();
//        app.startAppAtSpecificTime();



    }


    // Start at a specific time

//  public void startAppAtSpecificTime(){
//
//      LocalTime currentTime = LocalTime.now();
//      LocalTime desiredTime = LocalTime.of(13, 0); // Replace this with your desired start time
//
//      long delayInSeconds = currentTime.until(desiredTime, ChronoUnit.SECONDS);
//
//      if (delayInSeconds > 0) {
//          System.out.println("Waiting for " + delayInSeconds + " seconds until " + desiredTime);
//          try {
//              Thread.sleep(delayInSeconds * 1000); // Convert seconds to milliseconds
//          } catch (InterruptedException e) {
//              e.printStackTrace();
//          }
//      }
//
//      // Call the method to start the app or perform the test at the desired time
//      // startAppOrTest();
//      System.out.println("App or test started at " + LocalTime.now());
//  }



    @BeforeTest
    public void openTamasha() throws Exception {
//        startAppAtSpecificTime();
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Redmi 12C");
        cap.setCapability(MobileCapabilityType.UDID, "so7pjn9lovpnwssk");
        // OnePlus 7 pro = 1a8e4db6 // 061972513C014470 (Infinix X688B) // so7pjn9lovpnwssk Redmi   BYBY8SS8EQTOZTQ8  // R58W31MTKTJ //055832505J005791 // 115333741I003973 /Tecno Spark
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13");
        cap.setCapability("autoGrantPermissions", true);
        cap.setCapability("ignoreHiddenApiPolicyError", true);
        cap.setCapability("noReset", true); // Set noReset to true
        cap.setCapability("fullReset", false); // Set fullReset to false

        cap.setCapability("appPackage", "com.spbtv.mobilinktv");
        cap.setCapability("appActivity", "com.spbtv.mobilinktv.Splash.SplashActivityNew");


        URL url = new URL("http://127.0.0.1:4723/wd/hub/");
        driver = new AppiumDriver<MobileElement>(url, cap);

        System.out.println("Application has Started...!!!");

        File directory = new File("screenshots");
        if (!directory.exists()) {
            directory.mkdirs();
        }


    }


    @Test
    public void loginScenario() throws InterruptedException{
        loginFlow Login = new loginFlow();
        loginFlow.login(driver);
    }

    @Test
    public void redeemCoins() throws InterruptedException, IOException {

        coinsRedeem getTotalCoins = new coinsRedeem();
        getTotalCoins.getCount(driver);

    }
    @Test
    public void getCoinsCount() throws InterruptedException{

        getCoinsCount countCoins = new getCoinsCount();
        countCoins.printTotalCoins(driver);

    }

    @Test
    public void screenVer() throws InterruptedException {

        screenOrient screenAct = new screenOrient();
        screenAct.handleOrientation(driver);
    }

    @Test(priority = 5)
    public void liveStreaming() throws InterruptedException, IOException {

        liveTV liveStr = new liveTV();
        liveStr.switchToLiveTV(driver);
    }

    @Test
    public void screenScrolltoEnd() throws InterruptedException {

        scrollView scrollingtoEnd = new scrollView();
        scrollingtoEnd.screenScrolling(driver);
    }

    @Test(priority = 1)
    public void splashToHomeScreen() throws InterruptedException {

        splashTohome splashScr = new splashTohome();
        splashTohome.splashScrToHome(driver);

    }

    @Test
    public void HometoStreaming() throws InterruptedException, IOException{

        hometoLiveStreaming liveMatch = new hometoLiveStreaming();
        liveMatch.liveStreaming(driver);
    }

    @Test
    public void slider() throws InterruptedException{
        horizontalSlider horizontal = new horizontalSlider();
        horizontal.performSliderAction(driver);
    }

    @Test
    public void search() throws InterruptedException, IOException {

        SearchBar searchbar = new SearchBar();
        searchbar.searchFunctionality(driver);
    }

    @Test
    public void scrollandShot()throws InterruptedException {
        viewMoreSS ViewMore = new viewMoreSS();
        viewMoreSS.scrollAndSS(driver);
    }

    @Test(priority = 3)
    public void commentsPostingloop() throws InterruptedException{

        CommentAutomation PostCommentsinaLoop = new CommentAutomation();
        CommentAutomation.postComments(driver);

    }

    @Test
    public void verifyLiveStreaming() throws InterruptedException{

        verifyLiveStreaming.verifyLiveStreaming(driver);

    }
    @Test(priority = 4)
    public void fanpulseplay() throws InterruptedException {

        answertheQuestion FanPulseQA = new answertheQuestion();
        answertheQuestion.automateFanpulse(driver);

    }

    @Test
    public void howitWorks() throws IOException {

        fanPulseHowitWorks fanpulseHowTo = new fanPulseHowitWorks();
        fanPulseHowitWorks.howItworks(driver);

    }


    // Miniclip Package Scripts
    @Test
    public void reels() throws InterruptedException{

        reelsMiniclip shortVideos = new reelsMiniclip();
        reelsMiniclip.reel(driver);

    }

    @Test
    public void Vod() throws InterruptedException{

        VODfunctionality VideoOnDemand = new VODfunctionality();
        VODfunctionality.mainVOD(driver);

    }

    @Test
    public void deleteuserLoginagain() throws InterruptedException{

        deleteUser userDelete = new deleteUser();
        deleteUser.DeleteUserandSignInAgain(driver);

    }

    @Test
    public void FanPulsePlayFromHome() throws InterruptedException, IOException {

        playFanPulsefromHome fanpulsefromHome = new playFanPulsefromHome();
        playFanPulsefromHome.playFanPulse(driver);

    }

    @Test
    public void profileUpdate() throws InterruptedException {

        profileEdit edit = new profileEdit();
        edit.profileEdit(driver);

    }

    @Test(priority = 2)
    public void DynamicTile() throws InterruptedException{

        dTile Dtile = new dTile();
        dTile.VerifyTile(driver);

    }
    @Test
    public void Pakistan_vs_England() throws InterruptedException{

        scriptPakvsEng.pakvseng(driver);

    }
    @Test
    public void PIP() throws InterruptedException{
        LiveTV.PIP_Flow pipFlow = new PIP_Flow();
        pipFlow.pipmethod(driver);
}

@Test
public void RewardsLoadTime() throws InterruptedException{

        coins.loadTimeMyRewards loadTimeRewardsModule = new loadTimeMyRewards();
        loadTimeRewardsModule.rewardsScreenLoadTime(driver);

}

@Test
public void recommendationEngine() throws InterruptedException{
        verifyRecommendation.verifyREmain(driver);

}

    // End of Session

    @AfterTest
    public void tearDown() {
        System.out.println("Session closed!");
        if (driver != null) {
            driver.quit();
        }
    }

      // Sending email
//
//    @AfterSuite
//    public void sendemail() throws InterruptedException, IOException {
//        emailReport EmailReport = new emailReport();
//        emailReport.refreshAndSendEmail();
//
//        EmailDelivery.ExecuteBatchFile scp = new ExecuteBatchFile();
//        ExecuteBatchFile.main();
//
//    }

    //http://43.156.226.142/All%20Test%20Suite/Tamasha%20-%20TestNG%20Automation%20Report.html
    //http://43.156.226.142/


    }




