package com.cbt.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cbt.pages.HomePage;
import com.cbt.pages.ProfilesStatePage;
import com.cbt.utilities.BrowserUtils;
import com.cbt.utilities.ConfigurationReader;

public class SPA_574_TC extends TestBase {
	BrowserUtils br = new BrowserUtils();
	HomePage hp = new HomePage();
	ProfilesStatePage psp = new ProfilesStatePage();

	@Test(priority = 1, description = "Verifing current title")
	public void verificationTitle() {
		extentLogger = report.createTest("Verifing current title");
		driver.get(ConfigurationReader.getProperty("urlUSA"));
		String currentTitle = ConfigurationReader.getProperty("title");
		String actualTitle = driver.getTitle();
		Assert.assertEquals(currentTitle, actualTitle, "Verifing current title");
		extentLogger.pass("Verification of current title is pass");
	}

	@Test(priority = 2, description = "Profiles is clickble")
	public void hoverOver() throws InterruptedException {
		extentLogger = report.createTest("Profiles is clickble");
		Actions action = new Actions(driver);
		action.moveToElement(hp.profileMenu).perform();
		Assert.assertTrue(hp.profilesStateOption.isDisplayed());
		hp.profilesStateOption.click();
		extentLogger.pass("Profiles is clickble is pass");
	}

	@Test(priority = 3, description = "Verifing state url")
	public void statesUrl() {
		extentLogger = report.createTest("Verifing state url");
		String expectedUrl = ConfigurationReader.getProperty("urlState");
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl, actualUrl);
		extentLogger.pass("Verification  state url is pass");
	}

	@Test(priority = 4, description = "Verifing assending and decending order of statesAwardedAmount")
	public void sortStatesAwardedAmount() {
		extentLogger = report.createTest("Verifing assending and decending order of statesAwardedAmount");
		BrowserUtils.waitFor(1);
		psp.clickToGetAssendingOrder.click();
		List<Double> actualStatesAwardedAmountAssending = psp.convertingToDouble();
		List<Double> expectedStatesAwardedAmountAssending = new ArrayList<>();
		expectedStatesAwardedAmountAssending.addAll(actualStatesAwardedAmountAssending);
		Collections.sort(expectedStatesAwardedAmountAssending);
		Assert.assertEquals(expectedStatesAwardedAmountAssending, actualStatesAwardedAmountAssending);

		extentLogger.pass("Verification  assending order are pass");
		psp.clickToGetDecendingOrder.click();
		List<Double> actualStatesAwardedAmountDecending = psp.convertingToDouble();
		Collections.reverse(expectedStatesAwardedAmountAssending);
		System.out.println(expectedStatesAwardedAmountAssending);
		Assert.assertEquals(expectedStatesAwardedAmountAssending, actualStatesAwardedAmountDecending);
		extentLogger.pass("Verification decending order are pass");
	}
}