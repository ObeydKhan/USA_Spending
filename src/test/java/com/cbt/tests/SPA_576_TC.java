package com.cbt.tests;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cbt.pages.HomePage;
import com.cbt.pages.ProfilesStatePage;
import com.cbt.utilities.BrowserUtils;
import com.cbt.utilities.ConfigurationReader;

public class SPA_576_TC extends TestBase {
	BrowserUtils br = new BrowserUtils();
	HomePage hp = new HomePage();
	ProfilesStatePage psp = new ProfilesStatePage();

	@Test(priority = 1, description = "Title verification")
	public void verificationTitle() {
		extentLogger = report.createTest("Title verification");
		driver.get(ConfigurationReader.getProperty("urlUSA"));
		String currentTitle = ConfigurationReader.getProperty("title");
		String actualTitle = driver.getTitle();
		Assert.assertEquals(currentTitle, actualTitle, "Veriffying current title");
		extentLogger.pass("Verified title of the Home Page");
	}

	@Test(priority = 2, description = "Profiles is clickble")
	public void hoverOver() throws InterruptedException {
		extentLogger = report.createTest("Profiles is clickble");
		Actions action = new Actions(driver);
		action.moveToElement(hp.profileMenu).perform();
		Assert.assertTrue(hp.profilesStateOption.isDisplayed());
		extentLogger.pass("Verified Profile of the Home Page");
		hp.profilesStateOption.click();
	}

	@Test(priority = 3, description = "Check state url")
	public void statesUrl() {
		extentLogger = report.createTest("Check state url");
		String expectedUrl = ConfigurationReader.getProperty("urlState");
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl, actualUrl);
		extentLogger.pass("Verified state url of the Federal Page");
	}

	@Test(priority = 4, description = "Check number of results")
	public void numberOfResults() {
		extentLogger = report.createTest("Check number of results");
		BrowserUtils.waitFor(1);
		String resultLeftCorner = psp.LeftCornerResult.getText().replaceAll("[ results]", "");
		String resultCounted = String.valueOf(psp.statesAwardedAmount.size());
		Assert.assertEquals(resultLeftCorner, resultCounted);
		extentLogger.pass("Verified number of results");
	}
}
