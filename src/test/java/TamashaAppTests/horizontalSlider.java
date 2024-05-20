package TamashaAppTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import java.time.Duration;

public class horizontalSlider {

    public void performSliderAction(AppiumDriver<MobileElement> driver) throws InterruptedException {
        try {
            // Scroll to the bottom of the screen
            WebDriverWait wait = new WebDriverWait(driver, 10);
            scrollToBottom(driver);

            // Find the specific element to swipe
            MobileElement elementToSwipe = driver.findElement(MobileBy.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.spbtv.mobilinktv:id/relativeLayout\"])[6]"));
            elementToSwipe.isDisplayed();
            System.out.println(elementToSwipe.getText());
            Thread.sleep(5000);

            // Perform horizontal swipe action
            performHorizontalSwipe(driver, elementToSwipe);

            // Perform left swipe action
            performLeftSwipe(driver, elementToSwipe);

            System.out.println("Slider Working Successfully");
            Reporter.log("<b><p>Slider Working Successfully</p></b>");
            driver.closeApp();
            driver.launchApp();
        } catch (Exception excp) {
            System.out.println(excp.getMessage());
            driver.closeApp();
            driver.launchApp();
            Reporter.log("<b><p>Slider Stuck or Element could not be found</p></b>");
        }
    }

    // Scroll to the bottom of the screen
    private void scrollToBottom(AppiumDriver<MobileElement> driver) {
        WebElement bottomElement = driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollToEnd(2)"));

        int bottomY = bottomElement.getLocation().getY();
        int screenHeight = driver.manage().window().getSize().getHeight();

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(0, bottomY))
                .moveTo(PointOption.point(0, screenHeight / 3))
                .release()
                .perform();

        System.out.println("Scrolled Successfully");
        Reporter.log("<b><p>Scrolled Successfully</p></b>");
    }

    // Perform horizontal swipe action
    private void performHorizontalSwipe(AppiumDriver<MobileElement> driver, MobileElement elementToSwipe) {
        int elementStartX = elementToSwipe.getLocation().getX();
        int elementStartY = elementToSwipe.getLocation().getY();
        int elementWidth = elementToSwipe.getSize().getWidth();
        int elementHeight = elementToSwipe.getSize().getHeight();

        int startX = elementStartX + elementWidth - 10;
        int endX = elementStartX + 60;
        int centerY = elementStartY + elementHeight / 2;

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(startX, centerY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(endX, centerY))
                .release()
                .perform();

        // Add delay to observe the effect
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Perform left swipe action
    private void performLeftSwipe(AppiumDriver<MobileElement> driver, MobileElement elementToSwipe) {
        int elementStartX = elementToSwipe.getLocation().getX();
        int elementStartY = elementToSwipe.getLocation().getY();
        int elementWidth = elementToSwipe.getSize().getWidth();
        int elementHeight = elementToSwipe.getSize().getHeight();

        int startX = elementStartX + elementWidth - 10;
        int endX = elementStartX + 60;
        int centerY = elementStartY + elementHeight / 2;

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(startX, centerY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(endX, startX))
                .release()
                .perform();

        // Add delay to observe the effect
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
