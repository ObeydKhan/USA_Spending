package com.cbt.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cbt.pages.HomePage;
import com.cbt.utilities.ConfigurationReader;

public class SPA_261_TC extends TestBase {
	HomePage hp = new HomePage();

	@Test(priority = 1, description = "Launching to the home page")
	public void titleVerification() {
		extentLogger = report.createTest("Launch to the home page");
		// Launch the url
		driver.get(ConfigurationReader.getProperty("urlUSA"));
		// Verifing the title
		String actualTitle = ConfigurationReader.getProperty("title");
		String currentTitle = driver.getTitle();
		Assert.assertEquals(currentTitle, actualTitle);
		extentLogger.pass("Verify the title");
	}

	@Test(priority = 2, description = "Verifing the logo")
	public void logoVerification() {
		extentLogger = report.createTest("Verifing the logo");
		// Verifing is the logo displayed
		boolean logoVerification = hp.logo.isDisplayed();
		Assert.assertTrue(logoVerification);
		extentLogger.pass("Verify the logo is it displayed");
	}

	@Test(priority = 3, description = "Verifing is the main menu displayed")
	public void mainMenuDisplayed() {
		extentLogger = report.createTest("Verifing is the main menu displayed");
		// Verifing is the logo displayed
		boolean menuVerification = hp.mainMenu.isDisplayed();
		Assert.assertTrue(menuVerification);
		extentLogger.pass("Verify the main menu with WebElements displayed");
	}
}
