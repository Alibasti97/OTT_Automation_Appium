package extentPackage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Tamasha Test Results");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Automation Test Results");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Organization", "Mercurial Minds");
        extent.setSystemInfo("Project", "Tamasha");
        extent.setSystemInfo("Browser", "Chrome");
        return extent;


    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance("test-output/extent.html");
            createInstance("target/surefire-reports/extent.html");
        }
        return extent;
    }
}
