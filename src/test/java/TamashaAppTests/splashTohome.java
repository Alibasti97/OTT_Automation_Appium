package TamashaAppTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import HandlingLobby.handleLobby;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.StopWatch;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import EmailDelivery.dumpToExcel;




public class splashTohome {

    // Constants defining loop duration and interval between iterations
    private static final int LOOP_DURATION_MINUTES = 1; // Set Test Time in Minutes
    private static final int LOOP_INTERVAL_SECONDS = 15; // Time between each iteration (seconds)
    private static final int THRESHOLD_TIME_MILLISECONDS = 11; // Threshold time in milliseconds
    private static final int EMAIL_THRESHOLD_SECONDS = 14; // Email alert threshold in seconds

    private static final String EMAIL_USERNAME = "ali.hassan@mercurialminds.com";
    private static final String EMAIL_PASSWORD = "Basti@000";
    private static final String EMAIL_RECIPIENT = "alibasti2021@gmail.com";
    // Counter for iterations with load time exceeding 15 seconds
    private static final int consecutiveLoadTimeExceeds = 0;

    // Main function to initiate splash screen to home screen flow

    public static void splashScrToHome(AppiumDriver<MobileElement> driver) throws InterruptedException {

        splashToHome(driver);
    }

    public static void splashToHome(AppiumDriver<MobileElement> driver) throws InterruptedException {


        int countOverThreshold = 0; // Counter for load times greater than 11 seconds
        Reporter.log("<table border='1'>");
        Reporter.log("<tr><th bgcolor='#FFFF00'>Iteration No</th><th bgcolor='#FFFF00'>Load Time (Seconds)</th><th bgcolor='#FFFF00'>Current Date Time</th></tr>");

        // Variable to track the iteration count
        int iterationCount = 0;

        for (int i = 0; i < LOOP_DURATION_MINUTES * 60 / LOOP_INTERVAL_SECONDS; i++) {
            try {
                StopWatch loadTime = new StopWatch();
                LocalDateTime currentDateTime = LocalDateTime.now();
                loadTime.start();

                if (handleLobby.checkAndHandleLobby(driver)) {
                    System.out.println("Lobby was present and handled.");
                } else {
                    System.out.println("No lobby to handle or lobby handling complete.");
                }

                // Now wait for the main app element that indicates it's fully loaded
                waitForElement(driver, By.id("com.spbtv.mobilinktv:id/rv"), 120); // adjust this ID based on your application

                loadTime.stop();
                double loadTimeMillis = loadTime.getTime() / 1000.0;
                System.out.println("Load Time from Splash to Home Screen is: " + loadTimeMillis + " Seconds | " + currentDateTime);
                String textColor = (loadTimeMillis > 10) ? "red" : "purple"; // Set text color based on load time
                // Inside your for loop, after calculating loadTimeMillis

                // Write to Excel sheet only if it's not a lobby check

                // Only write to Excel sheet if it's not the first iteration
                if (iterationCount > 0) {
                    dumpToExcel.writeToExcel(loadTimeMillis, currentDateTime);
                }
                iterationCount++;


//
//                // Increment the consecutive load time exceeds counter
//                if (loadTimeMillis > EMAIL_THRESHOLD_SECONDS) {
//                    consecutiveLoadTimeExceeds++;
//                } else {
//                    // Reset the counter if load time is less than or equal to 15 seconds
//                    consecutiveLoadTimeExceeds = 0;
//                }
//
//                // If load time exceeds threshold for three consecutive iterations, send email alert
//                if (consecutiveLoadTimeExceeds >= 2) {
//                    sendEmailAlert(loadTimeMillis);
//                    // Reset the counter
//                    consecutiveLoadTimeExceeds = 0;
//                }
//
//
//                // Checking if load time exceeds threshold and incrementing the counter
//                if (loadTimeMillis > THRESHOLD_TIME_MILLISECONDS) {
//                    countOverThreshold++;
//                }


                TimeUnit.SECONDS.sleep(LOOP_INTERVAL_SECONDS);
                driver.closeApp();
                driver.launchApp();
                Reporter.log("<tr><td>" + (i + 1) + "</td><td><font color='" + textColor + "'>" + loadTimeMillis + "</font></td><td>" + currentDateTime + "</td></tr>");

                //                Reporter.log("Load Time from Splash to Home Screen is: " + loadTime.getTime() + " milliseconds | " + currentDateTime);
//                Reporter.log("<b><font color='purple'>Load Time from Splash to Home Screen is: " + loadTime.getTime() + " milliseconds | </font></b> " + currentDateTime);
//                Reporter.log("<b><font color='" + textColor + "'>Load Time from Splash to Home Screen is: " + loadTimeMillis + " milliseconds | </font></b> " + currentDateTime);

                // Trying Tabular Format Result!!!


            } catch (Exception e) {
                handleException(e, i, driver);
            } finally {
                System.out.println("Iteration " + (i + 1) + " Ended");
            }


        }

        Reporter.log("</table>");


        // Generate bar graph based on load times
        System.out.println("Number of times load time exceeded 11 seconds: " + countOverThreshold);
        Reporter.log("<h3><p>Number of times load time exceeded 11 seconds: </p></h3> " + countOverThreshold);
    }

    // Function to wait for the presence of a specific element
    private static void waitForElement(AppiumDriver<MobileElement> driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Function to log load time for each iteration
    private static void logLoadTime(StopWatch loadTime, int iteration) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Load Time from Splash to Home Screen in Iteration " + iteration + " is: " + loadTime.getTime() + " milliseconds, " + currentDateTime);
    }


    // Function to handle exceptions encountered during the iteration
    private static void handleException(Exception e, int iteration, AppiumDriver<MobileElement> driver) {

        // Differentiating between exception types and logging relevant information
        if (e instanceof TimeoutException || e instanceof NoSuchElementException || e instanceof WebDriverException) {
            Reporter.log("<b>App Stuck message:</b> " + e.getMessage());
            System.out.println("App Stuck message:" + e.getMessage());
            System.out.println("Cause:" + e.getCause());
        } else if (e instanceof InterruptedException) {
            Thread.currentThread().interrupt();
        }

        driver.closeApp();
        driver.launchApp();
    }


    // Function to send email alert for load times exceeding threshold
    private static void sendEmailAlert(double loadTimeSeconds) {
        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com"); // Outlook's SMTP server
        props.put("mail.smtp.port", "587"); // Port for TLS
        // Session
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_RECIPIENT));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("ali.asad@mercurialminds.com, jtnoc@mercurialminds.com, sarfraz.khan@mercurialminds.com, tahawar.ali@mercurialminds.com"));
            message.setSubject("Warning - Load Time Exceeded");
            message.setText("Load time exceeded 15 seconds. Load Time is: " + loadTimeSeconds + " seconds");

            // Send message
            Transport.send(message);
            System.out.println("Email alert sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email alert!");
        }
    }
}

