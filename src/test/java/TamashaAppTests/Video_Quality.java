//package TamashaAppTests;
//
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileElement;
//import org.openqa.selenium.By;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//
//public class Video_Quality {
//    By tileClick = new By.ByXPath("(//android.widget.RelativeLayout[@resource-id=\"com.spbtv.mobilinktv:id/relativeLayout\"])[2]");
//    By clickSettings = new By.ById("com.spbtv.mobilinktv:id/iv_quality");
//
//    By selectQuality_0_50mbps = new By.ByXPath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"480 × 360, 0.50 Mbps\"]");
//    By clickOkDialog = new By.ById("com.spbtv.mobilinktv:id/track_selection_dialog_ok_button");
//    By miniVid = new By.ById("com.spbtv.mobilinktv:id/back");
//    By closePlayer = new By.ById("com.spbtv.mobilinktv:id/iv_motion_close");
//    public void streamingQuality(AppiumDriver<MobileElement> driver) throws InterruptedException {
//
//        Thread.sleep(5000);
//        WebDriverWait waitTile = new WebDriverWait(driver, 10);
//        waitTile.until(ExpectedConditions.elementToBeClickable(tileClick)).click();
//
//        WebDriverWait waitSettings = new WebDriverWait(driver, 2);
//        waitSettings.until(ExpectedConditions.elementToBeClickable(clickSettings)).click();
//
//        WebDriverWait waitQuality = new WebDriverWait(driver, 10);
//        waitQuality.until(ExpectedConditions.elementToBeClickable(selectQuality_0_50mbps)).click();
//
//        System.out.println("Quality set to 480 x 360");
//
//        WebDriverWait waitokBtn = new WebDriverWait(driver, 10);
//        waitokBtn.until(ExpectedConditions.elementToBeClickable(clickOkDialog)).click();
//
//        WebDriverWait waitminimize = new WebDriverWait(driver, 20);
//        waitminimize.until(ExpectedConditions.elementToBeClickable(miniVid)).click();
//
//        WebDriverWait waitclosemini = new WebDriverWait(driver, 20);
//        waitclosemini.until(ExpectedConditions.elementToBeClickable(closePlayer)).click();
//        System.out.println("Live Streaming Ended!");
//
//    }
//}
