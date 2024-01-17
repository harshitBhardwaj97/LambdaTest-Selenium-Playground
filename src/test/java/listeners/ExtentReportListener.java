package listeners;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import base.Base;

public class ExtentReportListener implements ITestListener {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onStart(ITestContext context) {
		// Get the suite name
		String suiteName = context.getSuite().getName();

		LocalDateTime testExecutionDateTime = LocalDateTime.now();

		// Construct the report path based on the suite name
		String reportPath = Base.testOutput + "\\extent-" + suiteName + "_"
				+ DateTimeFormatter.ofPattern("ddMMM", Locale.ENGLISH).format(testExecutionDateTime) + "_"
				+ DateTimeFormatter.ofPattern("HHmmss", Locale.ENGLISH).format(testExecutionDateTime) + ".html";

		// Initialize ExtentReports with the constructed report path
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		extent = new ExtentReports();

//		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName(
				"Automation Framework developed using Java, Selenium and TestNG, written by Harshit Bhardwaj");
		sparkReporter.config().setDocumentTitle("LambdaTest Selenium Automation Results Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		extent.attachReporter(sparkReporter);
		System.out.println("Suite started: " + suiteName);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// Create a new ExtentTest for each test case
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().fail("Test failed: " + result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().skip("Test skipped: " + result.getThrowable());
	}

	// Additional method to get the current ExtentTest
	public static ExtentTest getTest() {
		return test.get();
	}
}
