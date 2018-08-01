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

public class SPA_573_TC extends TestBase {
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
	public void hoverOver() {
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

	@Test(priority = 4, description = "Verifing assending and decending order of states")
	public void sortStates() {
		extentLogger = report.createTest("Verifing assending and decending order of states");
		List<String> actualStatesAssending = new ArrayList<>();
		List<String> expectedStatesAssending = new ArrayList<>();
		psp.states.stream().forEach(x -> actualStatesAssending.add(x.getText().trim()));
		expectedStatesAssending.addAll(actualStatesAssending);
		Collections.sort(expectedStatesAssending);
		Assert.assertEquals(expectedStatesAssending, actualStatesAssending);
		BrowserUtils.waitFor(1);
		psp.clickToGetDecendingStatesOrder.click();
		Collections.reverse(expectedStatesAssending);
		Assert.assertEquals(expectedStatesAssending, actualStatesAssending);
		extentLogger.pass("Verification of  assending and decending order of states are pass");
	}
}
