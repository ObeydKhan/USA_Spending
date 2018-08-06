package com.cbt.tests;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cbt.pages.FederalAccountsPage;
import com.cbt.pages.HomePage;
import com.cbt.pages.ProfilesStatePage;
import com.cbt.utilities.BrowserUtils;
import com.cbt.utilities.ConfigurationReader;

public class SPA_572_TC extends TestBase {

	FederalAccountsPage fap = new FederalAccountsPage();
	HomePage hp = new HomePage();

	ProfilesStatePage psp = new ProfilesStatePage();

	@Test(priority = 1, description = "Title verification")
	public void verificationTitle() {
//		extentLogger = report.createTest("Title verification");

		driver.get(ConfigurationReader.getProperty("urlUSA"));
		String currentTitle = ConfigurationReader.getProperty("title");
		String actualTitle = driver.getTitle();
		Assert.assertEquals(currentTitle, actualTitle, "Veriffying current title");

//		extentLogger.pass("Verified title of the Home Page");
	}

	@Test(priority = 2, description = "Profiles is clickble")
	public void hoverOver() throws InterruptedException {

//		extentLogger = report.createTest("Profiles is clickble");
		Actions action = new Actions(driver);
		action.moveToElement(hp.profileMenu).perform();
		Assert.assertTrue(hp.profilesFederalAccountsOption.isDisplayed());
		hp.profilesFederalAccountsOption.click();

//		extentLogger.pass("Verified Profile of the Home Page");
	}

	@Test(priority = 3, description = "Verifying federal url")
	public void statesUrl() {

//		extentLogger = report.createTest("Verifying federal url");
		String expectedUrl = ConfigurationReader.getProperty("urlFederal");
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl, actualUrl);

//		extentLogger.pass("Verified federal url of the Federal Page");
	}

	@Test(priority = 4, description = "Verifying pager buttons")
	public void pagerButton() {

//		extentLogger = report.createTest("Verifying pager buttons");

		fap.nextPagerBtn.click();

		BrowserUtils.waitFor(2);

		Assert.assertTrue(fap.backPagerBtn.isEnabled());

//		extentLogger.pass("Verified pager button up");

		fap.backPagerBtn.click();

		Assert.assertTrue(!fap.backPagerBtn.isEnabled());

//		extentLogger.pass("Verified pager button down");
	}

}
