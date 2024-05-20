//package TamashaAppTests;
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileElement;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//import java.net.URL;
//import java.util.Scanner;
//
//public class tamasha {
//    static AppiumDriver<MobileElement> driver;
//    public static void main(String[] args) {
//
//        try {
//            openTamasha();
//            Login();
//            profile_name();
//            comments();
//            asiacup_to_live();
//            comments_after_minute();
//
//        } catch (Exception exp) {
//            System.out.println(exp.getMessage());
//            System.out.println(exp.getCause());
//        }
//
//    }
//    @BeforeTest
//    public static void openTamasha() throws Exception {
//        DesiredCapabilities cap = new DesiredCapabilities();
//        cap.setCapability("deviceName", "OPPO A54");
//        cap.setCapability("udid", "so7pjn9lovpnwssk");
//        cap.setCapability("udid", "BYBY8SS8EQTOZTQ8");
//
//        cap.setCapability("platformName", "Android");
//        cap.setCapability("platformVersion", "10");
//        cap.setCapability("autoGrantPermissions", true);
//        cap.setCapability("ignoreHiddenApiPolicyError", true);
//
//
//        cap.setCapability("appPackage", "com.spbtv.mobilinktv");
//        cap.setCapability("appActivity", "com.spbtv.mobilinktv.Splash.SplashActivityNew");
//
//        URL url = new URL("http://127.0.0.1:4723/wd/hub/");
//        driver = new AppiumDriver<>(url, cap);
//
//        System.out.println("Application has Started...!!!");
//    }
//
//
//    @Test
//    public static void Login() throws Exception {
//
//
//        boolean isLobbyEnabled = false;
//
//        if (isLobbyEnabled) {
//            System.out.println("The lobby is enabled.");
//            WebDriverWait wait = new WebDriverWait(driver, 200);
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.ImageView"))).click();
//
//        } else {
//            System.out.println("The lobby is disabled.");
//        }
//
//        System.out.println("User arrived at home screen");

//        WebDriverWait wait1 = new WebDriverWait(driver, 50);
//        wait1.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/item_profile_home"))).click();
//
//        System.out.println("User arrived at More screen");
//
//        WebDriverWait wait2 = new WebDriverWait(driver, 10);
//        wait2.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/profile_image"))).click();
//
//        System.out.println("User arrived at profile screen");
//
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter your TELCO:");
//        System.out.println("Press Z for Zong");
//        System.out.println("Press J for Jazz");
//        System.out.println("Press T for Telenor");
//        System.out.println("Press U for Ufone");
//
//
//        char choice = scanner.next().charAt(0);
//
//        if (choice == 'Z' || choice == 'z') {
//
//            System.out.println("You selected Zong.");
//            WebDriverWait wait4 = new WebDriverWait(driver, 10);
//            wait4.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/img_unselected_zong"))).click();
//
//        } else if (choice == 'J' || choice == 'j') {
//            System.out.println("You selected Jazz.");
//            WebDriverWait wait400 = new WebDriverWait(driver, 10);
//            wait400.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/img_unselected_jazz"))).click();
//
//        } else if (choice == 'T' || choice == 't') {
//            System.out.println("You selected Telenor.");
//            WebDriverWait wait401 = new WebDriverWait(driver, 10);
//            wait401.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/img_unselected_telenor"))).click();
//
//        } else if (choice == 'U' || choice == 'u') {
//            System.out.println("You selected Ufone.");
//            WebDriverWait wait402 = new WebDriverWait(driver, 10);
//            wait402.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/img_unselected_ufone"))).click();
//
//        } else {
//            System.out.println("Invalid choice. Please select Z, J, T, or U.");
//        }
//
//        System.out.println("Number Field!!");
//
//
//        WebDriverWait wait5 = new WebDriverWait(driver, 30);
//        WebElement number = wait5.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/et_input_number")));
//
//        String usernumberInput = scanner.next();
//
//        number.sendKeys(usernumberInput);
//
//        WebDriverWait wait6 = new WebDriverWait(driver, 10);
//        wait6.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/btn_next"))).click(); //Continue button clicked
//
//
//        System.out.println("Enter OTP and Click Verify");
//        WebDriverWait wait10 = new WebDriverWait(driver, 80);
//        {
//            WebElement OTP1 = wait10.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/et_input_number_1")));
//            System.out.print("Enter OTP : ");
//            String userOTPInput1 = scanner.next();
//            OTP1.sendKeys(userOTPInput1);
//
//            WebElement OTP2 = wait10.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/et_input_number_2")));
//            System.out.print("Enter OTP : ");
//            String userOTPInput2 = scanner.next();
//            OTP2.sendKeys(userOTPInput2);
//
//            WebElement OTP3 = wait10.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/et_input_number_3")));
//            System.out.print("Enter OTP : ");
//            String userOTPInput3 = scanner.next();
//            OTP3.sendKeys(userOTPInput3);
//
//            WebElement OTP4 = wait10.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/et_input_number_4")));
//            System.out.print("Enter OTP : ");
//            String userOTPInput4 = scanner.next();
//            OTP4.sendKeys(userOTPInput4);
//
//        }
//        WebDriverWait wait11 = new WebDriverWait(driver, 10);
//        wait11.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/tv_send"))).click(); //Verify button clicked
//
//    }
//
//    @Test
//    public static void profile_name() throws InterruptedException {
//
//        Scanner scanner = new Scanner(System.in);
//
//        WebDriverWait wait1110 = new WebDriverWait(driver, 10);
//        wait1110.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/item_profile_home"))).click();
//
//
//        System.out.println("User arrived at More screen");
//
//        WebDriverWait wait21 = new WebDriverWait(driver, 10);
//        wait21.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/profile_image"))).click();
//
//        System.out.println("User arrived at profile screen");
//
//        WebDriverWait wait31 = new WebDriverWait(driver, 10);
//        wait31.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/tv_edit"))).click();
//
//
//        System.out.println("User arrived at profile EDIT screen");
//
//        WebDriverWait wait8 = new WebDriverWait(driver, 20);
//        WebElement name = wait8.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/et_full_name")));
//        name.clear();
//        System.out.println("Enter your Username");
//        String usernameInput = scanner.next();
//
//        if ("MM".equalsIgnoreCase(usernameInput)) {
//            // Use default input
//            name.sendKeys("MM");
//        } else {
//            // Use user-defined input
//            name.sendKeys(usernameInput);
//        }
//
//        name.sendKeys(usernameInput);
//        Thread.sleep(3000);
//
//        WebDriverWait wait9 = new WebDriverWait(driver, 20);
//        wait9.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/tv_update"))).click();
//
//
//        WebDriverWait wait71 = new WebDriverWait(driver, 10);
//        wait71.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/back"))).click();
//
//        WebDriverWait wait72 = new WebDriverWait(driver, 10);
//        wait72.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/back"))).click();
//
//        System.out.println("PROFILE NAME UPDATED!!!!!!!!!");
//    }
//
//    @Test
//    public static void AsiaCup_tile() {
//
//
//        WebDriverWait wait771 = new WebDriverWait(driver, 10);
//        wait771.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/back"))).click();
//
//        WebDriverWait wait772 = new WebDriverWait(driver, 10);
//        WebElement AsiaCup_tile = wait772.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/btn_asiacup")));
//        AsiaCup_tile.click();
//
//    }
//
//    @Test
//    public static void comments() throws InterruptedException {
//        Thread.sleep(1000);
//
//
//
//        WebDriverWait wait73 = new WebDriverWait(driver, 30);
//        WebElement asia_cup = wait73.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout")));
//        asia_cup.click();
//
//        WebDriverWait wait74 = new WebDriverWait(driver, 10);
//        wait74.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[3]/android.widget.ImageView"))).click();
//
//        WebDriverWait wait75 = new WebDriverWait(driver, 10);
//        WebElement comment = wait75.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/etComment")));
//
//        comment.click();
//        comment.sendKeys(" Hello Guys!");
//        WebDriverWait wait76 = new WebDriverWait(driver, 10);
//        wait76.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/ivSent"))).click();
//        Thread.sleep(2000);
//        WebDriverWait wait81 = new WebDriverWait(driver, 20);
//        wait81.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/ivClose"))).click();
//
//        driver.findElement(By.id("com.spbtv.mobilinktv:id/ly_volume")).click();
//
//        WebDriverWait wait82 = new WebDriverWait(driver, 10);
//        wait82.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.ImageView"))).click();
//
//        driver.findElement(By.id("com.spbtv.mobilinktv:id/iv_motion_close")).click();
//
//    }
//
//    @Test
//    public static void asiacup_to_live() throws InterruptedException {
//
//        WebDriverWait wait731 = new WebDriverWait(driver, 30);
//        WebElement asia_cup = wait731.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout")));
//        asia_cup.click();
//
//        System.out.println("User arrived at Asia Cup");
//
//        Thread.sleep(5000);
//
//        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]")).click();
//
//
//        driver.findElement(By.id("com.spbtv.mobilinktv:id/ly_volume")).click();
//        System.out.println("User clicks on screen");
//
//
//        WebDriverWait wait82 = new WebDriverWait(driver, 10);
//        wait82.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.ImageView"))).click();
//
//
//
//        // Going on live tv
//        System.out.println(" In Live tv tab ");
//        driver.findElement(By.id("com.spbtv.mobilinktv:id/item_live_channel")).click();
//
//
//        System.out.println("User arrived at BOl TV");
//
//        WebDriverWait wait822 = new WebDriverWait(driver, 10);
//        wait822.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[3]/android.widget.RelativeLayout/android.widget.RelativeLayout"))).click();
//        Thread.sleep(5000);
//
//
//        driver.findElement(By.id("com.spbtv.mobilinktv:id/ly_volume")).click();
//
//
//        System.out.println("User clicks on Home Tab");
//
//        WebDriverWait wait832 = new WebDriverWait(driver, 10);
//        wait832.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/item_home"))).click();
//
//    }
//
//    @Test
//    public static void comments_after_minute() throws InterruptedException {
//        System.out.println("In comments per minute test");
//        Thread.sleep(1000);
//        WebDriverWait wait96 = new WebDriverWait(driver, 10);
//        wait96.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/relativeLayout"))).click();
//
//        WebDriverWait wait97 = new WebDriverWait(driver, 30);
//        wait97.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[3]/android.widget.ImageView"))).click();
//
//
//
//        WebDriverWait wait79 = new WebDriverWait(driver, 10);
//        WebElement comment = wait79.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/etComment")));
//
//
//        int numberOfIterations = 10;
//        for (int i = 0; i < numberOfIterations; i++) {
//
//            //comment.click();
//            comment.sendKeys(" PAKISTAN ZINDABAD ");
//
//            WebDriverWait wait80 = new WebDriverWait(driver, 10);
//            wait80.until(ExpectedConditions.elementToBeClickable(By.id("com.spbtv.mobilinktv:id/ivSent"))).click();
//
//            System.out.println("Iteration " + (i + 1));
//            //Thread.sleep(500);
//        }
//
//        driver.findElement(By.id("com.spbtv.mobilinktv:id/ly_volume")).click();
//
//
//        WebDriverWait wait82 = new WebDriverWait(driver, 10);
//        wait82.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.ImageView"))).click();
//
//        driver.findElement(By.id("com.spbtv.mobilinktv:id/iv_motion_close")).click();
//
//    }
//
//    @AfterTest
//    public void tearDown() {
//        System.out.println("Session closed!");
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//}
