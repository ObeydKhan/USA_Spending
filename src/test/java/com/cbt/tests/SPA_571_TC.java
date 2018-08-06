package com.cbt.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cbt.pages.FederalAccountsPage;
import com.cbt.pages.HomePage;
import com.cbt.pages.ProfilesStatePage;
import com.cbt.utilities.BrowserUtils;
import com.cbt.utilities.ConfigurationReader;

public class SPA_571_TC extends TestBase {
	BrowserUtils br = new BrowserUtils();
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

	@Test(priority = 4, description = "Check assending and decending order")
	public void sortBudgetaryResources() {
//		extentLogger = report.createTest("Check assending and decending order");
		BrowserUtils.waitFor(4);
		fap.clickToGetAssendingOrder.click();
		BrowserUtils.waitFor(1);
		List<Double> actualFederalBudgetaryResources = fap.convertingToDouble();
		List<Double> expectedFederalBudgetaryResourcesAssending = new ArrayList<>();
		expectedFederalBudgetaryResourcesAssending.addAll(actualFederalBudgetaryResources);
		// System.out.println(actualFederalBudgetaryResources);
		// System.out.println(expectedFederalBudgetaryResourcesAssending);
		Collections.sort(expectedFederalBudgetaryResourcesAssending);
		Assert.assertEquals(expectedFederalBudgetaryResourcesAssending, actualFederalBudgetaryResources);
//		extentLogger.pass("Verified scending order");
		fap.clickToGetDecendingOrder.click();
		BrowserUtils.waitFor(1);
		List<Double> actualFederalBudgetaryResources1 = fap.convertingToDouble();
		List<Double> expectedFederalBudgetaryResourcesDescending = new ArrayList<>();
		expectedFederalBudgetaryResourcesDescending.addAll(actualFederalBudgetaryResources1);
		Collections.sort(expectedFederalBudgetaryResourcesDescending);
		Collections.reverse(expectedFederalBudgetaryResourcesDescending);
		// System.out.println(expectedFederalBudgetaryResourcesDescending);
		// System.out.println(actualFederalBudgetaryResources1);
		Assert.assertEquals(expectedFederalBudgetaryResourcesDescending, actualFederalBudgetaryResources1);
//		extentLogger.pass("Verified descending order");
	}
}
