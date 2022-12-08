package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	static ExtentReports extent;

	public static ExtentReports getReporter() {
		String filePath = System.getProperty("user.dir") + "//Reports//ExtentReport.html";
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(filePath);
		//ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
		reporter.config().setReportName("RESTASSURED API Automation Framework Results");
		reporter.config().setDocumentTitle("API Automation Test Results");
		reporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("QA Automation Engineer", "Eddy Nziraguhindwa");
		extent.setSystemInfo("Project Name", "API Automation Framework With RestAssured");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.createTest(filePath);
		return extent;
	}

}
