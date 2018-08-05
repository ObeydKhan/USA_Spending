package com.cbt.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cbt.utilities.Driver;

public class ProfilesStatePage {
	private WebDriver driver;

	public ProfilesStatePage() {
		driver = Driver.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[contains(text(),'Sort table by ascending percent of total')]/..")
	public WebElement arrowPercentOfTotalUp;

	@FindBy(xpath = "//*[contains(text(),'Sort table by descending percent of total')]/..")
	public WebElement arrowPercentOfTotalDown;

	@FindBy(xpath = "//tr[@class='state-list__body-row']/td[3]")
	public List<WebElement> percentOfTotalAllColumnInfo;

	@FindBy(xpath = "//tr[@class='state-list__body-row']/td[2]")
	public List<WebElement> statesAwardedAmount;
	
	@FindBy(xpath = "//*[contains(text(),'Sort table by ascending awarded amount')]/..")
	public WebElement clickToGetAssendingOrder;
	
	@FindBy(xpath = "//*[contains(text(),'Sort table by descending awarded amount')]/..")
	public WebElement clickToGetDecendingOrder;
	
	@FindBy(xpath = "//tr[@class='state-list__body-row']/td[1]")
	public List<WebElement> states;
	
	@FindBy(xpath = "//*[contains(text(),'Sort table by descending state')]/..")
	public WebElement clickToGetDecendingStatesOrder; ////button[2]

	@FindBy(xpath = "//*[@id='main-content']/div/div[2]/div[2]")
	public WebElement LeftCornerResult;

	/*
	 * this method converting the given states Amount from String to Double and
	 * returns List of double
	 */
	public List<Double> convertingToDouble() {
		List<Double> prices = new ArrayList<>();
		for (WebElement price : statesAwardedAmount) {
			String priceStr = price.getText().replace("$", "").replace(",", "");
			prices.add(Double.valueOf(priceStr));
		}
		return prices;
	}

}
