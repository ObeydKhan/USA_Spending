package com.cbt.tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cbt.pages.HomePage;
import com.cbt.utilities.ConfigurationReader;
import com.cbt.utilities.Driver;

public class Smoke_test extends TestBase{

	HomePage hp = new HomePage();
	Actions action = new Actions(Driver.getDriver());
	
	@Test(priority = 1, description = "Launching to the home page")
	public void titleVerification() {
//		extentLogger = report.createTest("Launch to the home page");
		// Launch the url
		driver.get(ConfigurationReader.getProperty("urlUSA"));
		// Verifing the title
		String actualTitle = ConfigurationReader.getProperty("title");
		String currentTitle = driver.getTitle();
		Assert.assertEquals(currentTitle, actualTitle);
//		extentLogger.pass("Verify the title");
	}

	@Test(priority = 2, description = "Verifing the logo")
	public void logoVerification() {
//		extentLogger = report.createTest("Verifing the logo");
		// Verifing is the logo displayed
		hp = new HomePage();
		driver.get(ConfigurationReader.getProperty("urlUSA"));
		boolean logoVerification = hp.logo.isDisplayed();
		Assert.assertTrue(logoVerification);
//		extentLogger.pass("Verify the logo is it displayed");
	}

	@Test(priority = 3, description = "Verifing is the main menu displayed")
	public void mainMenuDisplayed() {
//		extentLogger = report.createTest("Verifing is the main menu displayed");
		// Verifing is the logo displayed
		boolean menuVerification = hp.mainMenu.isDisplayed();
		Assert.assertTrue(menuVerification);
//		extentLogger.pass("Verify the main menu with WebElements displayed");
	}
	
	@Test(priority = 2, description = "Hover over the PROFILE functionality to reach Federal Accounts link")
	public void openFederalAccounts() {
// 		extentLogger = report.createTest("Hover over the PROFILE functionality to reach Federal Accounts link");
 		action.moveToElement(hp.profileMenu).build().perform();
 		hp.profilesFederalAccountsOption.click();
 		String actualUrl = driver.getCurrentUrl();
 		String expectedUrl = ConfigurationReader.getProperty("urlFederal");
 		assertEquals(actualUrl, expectedUrl, "Verify url of the Federal Accounts page");
// 		extentLogger.pass("Verify url of the Federal Accounts page");
 	}
	
}
