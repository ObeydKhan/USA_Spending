package com.cbt.tests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.cbt.pages.FederalAccountsPage;
import com.cbt.pages.HomePage;
import com.cbt.utilities.BrowserUtils;
import com.cbt.utilities.ConfigurationReader;
import com.cbt.utilities.Driver;

public class SPA_567_TC extends TestBase {
 	Actions action = new Actions(Driver.getDriver());
 	HomePage hp = new HomePage();
 	FederalAccountsPage fap = new FederalAccountsPage();
 	
 	@Test(priority = 1, description = "Home page title verification")
	public void titleVerification() {
 		// name of the test for the report
		extentLogger = report.createTest("Home page title verification");
 		driver.get(ConfigurationReader.getProperty("urlUSA"));
 		// actual title
		String actualTitle = driver.getTitle();
 		// expected title
		String expectedTitle = ConfigurationReader.getProperty("title");
 		assertEquals(actualTitle, expectedTitle, "Verify title of the home page");
 		// name for the verification
		extentLogger.pass("Verified title of the Home Page");
 	}
 	@Test(priority = 2, description = "Hover over the PROFILE functionality to reach Federal Accounts link")
	public void openFederalAccounts() {
 		extentLogger = report.createTest("Hover over the PROFILE functionality to reach Federal Accounts link");
 		action.moveToElement(hp.profileMenu).build().perform();
 		hp.profilesFederalAccountsOption.click();
 		String actualUrl = driver.getCurrentUrl();
 		String expectedUrl = ConfigurationReader.getProperty("urlFederal");
 		assertEquals(actualUrl, expectedUrl, "Verify url of the Federal Accounts page");
 		extentLogger.pass("Verify url of the Federal Accounts page");
 	}
 	@Test(priority = 3, description = "Check after click on Account Number btn gives descending order")
	public void descendingOrder() {
 		extentLogger = report.createTest("Check after click on Account Number btn gives descending order");
 		BrowserUtils.waitFor(1);
 		fap.accountNumBtn.click();
 		BrowserUtils.waitFor(1);
 		List<String> actualResultOfNumber = new ArrayList<>();
 		fap.accountNumColomn.stream().forEach(y -> actualResultOfNumber.add(y.getText().substring(0, 3)));
 		List<String> reverseResultOfNumber = new ArrayList<>();
 		fap.accountNumColomn.stream().forEach(x -> reverseResultOfNumber.add(x.getText().substring(0, 3)));
 		Collections.sort(reverseResultOfNumber);
 		Collections.reverse(reverseResultOfNumber);
 		assertEquals(actualResultOfNumber, reverseResultOfNumber, "verify descending order");
 		extentLogger.pass("Verify descending order");
 	}
 	@Test(priority = 4, description = "Check after click on Account Number btn gives ascending order")
	public void desendingOrder() {
 		extentLogger = report.createTest("Check after click on Account Number btn gives ascending order"); // report
 		fap.accountNumBtn.click();
 		BrowserUtils.waitFor(1);
 		List<String> actualResultOfNumber = new ArrayList<>();
 		fap.accountNumColomn.stream().forEach(y -> actualResultOfNumber.add(y.getText().substring(0, 3)));
 		List<String> sortedResultOfNumber = new ArrayList<>();
 		fap.accountNumColomn.stream().forEach(x -> sortedResultOfNumber.add(x.getText().substring(0, 3)));
 		Collections.sort(sortedResultOfNumber);
 		assertEquals(actualResultOfNumber, sortedResultOfNumber, "verify descending order");
 		extentLogger.pass("Verify ascending order"); // report pass/fail
 	}
 }
