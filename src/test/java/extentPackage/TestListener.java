package extentPackage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.List;

public class TestListener implements ITestListener {
    private ExtentReports extent = ExtentManager.getInstance();
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        attachDetails(result, Status.PASS);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        attachDetails(result, Status.FAIL);
    }

    private void attachDetails(ITestResult result, Status status) {
        List<String> outputMessages = Reporter.getOutput(result);
        ExtentTest extentTest = test.get();
        StringBuilder htmlContent = new StringBuilder();

        outputMessages.forEach(message -> {
            if (message.contains("<img")) { // Specifically checks for image tags
                // Append with a style to maintain aspect ratio and set max dimensions
                htmlContent.append(message.replace("<img", "<img style='max-width:50%; height:50%;'")).append("<br>");
            } else if (message.contains("<")) { // Handles other HTML tags
                htmlContent.append(message).append("<br>");
            }
        });

        // Log all HTML content at once
        if (htmlContent.length() > 0) {
            extentTest.log(status, "HTML Output: " + htmlContent.toString());
        }



//         // Method to attach screenshots and log them only once
//        attachScreenshots(result, extentTest, status);
    }

    private void attachScreenshots(ITestResult result, ExtentTest extentTest, Status status) {
        List<String> outputMessages = Reporter.getOutput(result);
        outputMessages.stream()
                .filter(message -> message.contains("src=\"data:image/png;base64,"))
                .forEach(message -> {
                    String base64Image = message.substring(message.indexOf("base64,") + 7, message.indexOf("\"", message.indexOf("base64,") + 7));
                    try {
                        extentTest.log(status, "Screenshot:",
                                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
                    } catch (Exception e) {
                        extentTest.log(status, "Failed to attach screenshot: " + e.getMessage());
                    }
                });
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
