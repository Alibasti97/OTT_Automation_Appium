//package HandlingLobby;
//
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileElement;
//import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class handleLobby {
//
//    // Static variable to store the lobby status
//    private static Boolean lobbyEnabled = null;
//    public static boolean firstCheck = true; // Flag to track the first check
//
//
//    /**
//     * Checks if the lobby is enabled and visible on the app screen for the first time.
//     * If the lobby is present, it interacts with it by clicking.
//     * Subsequent checks will return the initial result without rechecking.
//     *
//     * @param driver the Appium driver used to interact with mobile elements.
//     * @return true if the lobby is enabled and was clicked, otherwise false.
//     */
//    public static boolean checkAndHandleLobby(AppiumDriver<MobileElement> driver) {
//        // Check if we have already determined the lobby status
//        if (lobbyEnabled != null) {
//            return lobbyEnabled;
//        }
//
//        lobbyEnabled = false;  // Default to false until proven otherwise
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, 10);
//            MobileElement lobbyElement = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.spbtv.mobilinktv:id/btn_tamasha")));
//
//            if (lobbyElement != null && lobbyElement.isDisplayed()) {
//                lobbyElement.click();  // Click the lobby element
//                System.out.println("Lobby is enabled and clicked.");
//                lobbyEnabled = true;
//            }
//        } catch (NoSuchElementException e) {
//            if (firstCheck) {
//                System.out.println("Lobby element not found: " + e.getMessage());
//            }
//        } catch (Exception e) {
//            if (firstCheck) {
//                System.out.println("Lobby element not found: " + e.getMessage());
//
//                System.out.println("An error occurred while checking the lobby: " + e.getMessage());
//            }
//        }
//
//        if (firstCheck) {
//            firstCheck = false; // Reset the flag after the first run
//        }
//
//        return lobbyEnabled;
//    }
//}
//
//





package HandlingLobby;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class handleLobby {

    private static Boolean lobbyInitiallyFound = null;  // Track if the lobby was found initially

    public static boolean checkAndHandleLobby(AppiumDriver<MobileElement> driver) {
        if (lobbyInitiallyFound != null && !lobbyInitiallyFound) {
            // If the lobby was never found initially, skip checks
            return false;
        }

        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            MobileElement lobbyElement = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.spbtv.mobilinktv:id/btn_tamasha")));

            if (lobbyElement != null && lobbyElement.isDisplayed()) {
                lobbyElement.click();
                System.out.println("Lobby is enabled and clicked.");
                if (lobbyInitiallyFound == null) {
                    lobbyInitiallyFound = true;  // Mark that lobby was found initially
                }
                return true;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Lobby element not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lobby Element Not Found");
        }

        if (lobbyInitiallyFound == null) {
            lobbyInitiallyFound = false;  // Mark that lobby was not found initially
        }
        return false;
    }
}
