package com.cbt.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cbt.utilities.BrowserUtils;
import com.cbt.utilities.ConfigurationReader;
import com.cbt.utilities.Driver;

public abstract class TestBase {
	protected WebDriver driver;
	protected ExtentReports report;
	protected ExtentHtmlReporter htmlReporter;
	protected ExtentTest extentLogger;

	@BeforeClass
	public void setUp() {
		driver = Driver.getDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	@BeforeTest
	public void setUpTest() {
		report = new ExtentReports();
		String filePath = System.getProperty("user.dir") + "/test-output/report.html";
		htmlReporter = new ExtentHtmlReporter(filePath);
		report.attachReporter(htmlReporter);
		report.setSystemInfo("ENV", "staging");
		report.setSystemInfo("browser", ConfigurationReader.getProperty("browser"));
		report.setSystemInfo("OS", System.getProperty("os.name"));
		htmlReporter.config().setReportName("Web Orders Automated Test Reports");
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		// checking if the test method failed
		if (result.getStatus() == ITestResult.FAILURE) {
			// get screenshot using the utility method and save the location of the
			// screenshot
			String screenshotLocation = BrowserUtils.getScreenshot(result.getName());
			// capture the name of test method
			extentLogger.fail(result.getName());
			// add the screenshot to the report
			extentLogger.addScreenCaptureFromPath(screenshotLocation);
			// capture the exception thrown
			extentLogger.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentLogger.skip("Test Case Skipped is " + result.getName());
		}
	}

	@AfterTest
	public void tearDownTest() {
		report.flush();
	}

	@AfterClass
	public void setUpClose() {
		Driver.closeDriver();
	}
}