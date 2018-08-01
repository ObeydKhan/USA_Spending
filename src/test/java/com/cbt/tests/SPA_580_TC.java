package com.cbt.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cbt.pages.FederalAccountsPage;
import com.cbt.pages.HomePage;
import com.cbt.utilities.ConfigurationReader;
import com.cbt.utilities.Driver;

public class SPA_580_TC extends TestBase {
	Actions action = new Actions(Driver.getDriver());

	FederalAccountsPage fap = new FederalAccountsPage();
	HomePage hp = new HomePage();

	@Test(priority = 1, description = "Hover over the PROFILE functionality to reach Federal Accounts")
	public void sortedFunctionality() {
		extentLogger = report.createTest("Hover over the PROFILE functionality to reach Federal Accounts");
		// Navigate to Profile then Federal Accounts and click on the last
		driver.get(ConfigurationReader.getProperty("urlUSA"));
		action.moveToElement(hp.profileMenu).build().perform();
		hp.profilesFederalAccountsOption.click();
		extentLogger.pass("Navigate to Profile then Federal Accounts and click on the last");
		// Asserting url
		String currentUrlForFederalAccountProfile = driver.getCurrentUrl();
		String acctualUrlForFederalAccountProfile = ConfigurationReader.getProperty("urlFederal");
		Assert.assertEquals(currentUrlForFederalAccountProfile, acctualUrlForFederalAccountProfile);
		extentLogger.pass("Verify the current url is assert to actual url");
		// Asserting title
		extentLogger = report.createTest("Check title in order to verify right page");
		String actualTitle = ConfigurationReader.getProperty("title");
		String currentTitle = driver.getTitle();
		Assert.assertEquals(currentTitle, actualTitle);
		extentLogger.pass("Verify the current title is assert to actual title on this page");
		// Checking ascending order
		List<Integer> actualList = fap.convertingToInteger();
		List<Integer> expRes = new ArrayList<>();
		expRes.addAll(actualList);
		Collections.sort(expRes);
		fap.descendAccountNumBtn.click();
		extentLogger = report
				.createTest("Before comparing ascending and actual >results from Account Number functionality");
		Assert.assertNotEquals(actualList, expRes);
		extentLogger.pass(
				"After comparing ascending and actual results from Account Number functionality and having result");
		// //Checking descending order
		// extentLogger = report.createTest("Before comparing descending and actual
		// >results from Account Number functionality");
		// hp.descendAccountNumBtn.click();
		//
		// Assert.assertEquals(actualList, expRes);
		// extentLogger.pass("After comparing descending and actual results from Account
		// Number functionality and having result");
	}
}
