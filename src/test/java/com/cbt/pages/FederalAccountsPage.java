package com.cbt.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cbt.utilities.Driver;

public class FederalAccountsPage {
	private WebDriver driver;

	public FederalAccountsPage() {
		driver = Driver.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id=\"main-content\"]/div/div[3]/ul/li[6]/button")
	public WebElement nextPagerBtn;

	@FindBy(xpath = "//*[@id=\"main-content\"]/div/div[3]/ul/li[1]/button")
	public WebElement backPagerBtn;

	@FindBy(xpath = "//tbody//tr[@class='results-table__row']/td[4]//div//div")
	public List<WebElement> federalBudgetaryResources;

	@FindBy(xpath = "//*[contains(text(),'Sort table by ascending 2018 Budgetary Resources')]/..")
	public WebElement clickToGetAssendingOrder;

	@FindBy(xpath = "//*[contains(text(),'Sort table by descending 2018 Budgetary Resources')]/..")
	public WebElement clickToGetDecendingOrder;

	public List<Double> convertingToDouble() {
		System.out.println("First size: " + federalBudgetaryResources.size());
		System.out.println("Index: " + federalBudgetaryResources.get(0).getText());
		System.out.println("Index: " + federalBudgetaryResources.get(4).getText());
		System.out.println("Index: " + federalBudgetaryResources.get(5).getText());
		List<Double> prices = new ArrayList<>();
		for (WebElement price : federalBudgetaryResources) {
			String priceStr = price.getText().replace("$", "").replace(",", "");
			if (Double.valueOf(priceStr) == 0)
				prices.add(0d);
			else
				prices.add(Double.valueOf(priceStr));
		}
		System.out.println("Size: " + prices.size());
		return prices;
	}

	@FindBy(xpath = "//div[contains(text(),'Managing Agency')]")
	public WebElement managingAgency;
	@FindBy(xpath = "//div[@class='results-table-cell results-table-cell_column_accountNumber']/div")
	public List<WebElement> accountNumColomn;

	@FindBy(xpath = "//div[contains(text(),'Account Number')]")
	public WebElement accountNumBtn;

	/*
	 * method to convert String to Integer
	 */
	public List<Integer> convertingToInteger() {
		List<Integer> accountNums = new ArrayList<>();
		for (WebElement price : accountNumColomn) {
			String accNumCol = price.getText().replace("-", "").trim();
			accountNums.add(Integer.valueOf(accNumCol));
		}
		return accountNums;
	}

	@FindBy(xpath = "//div[@class='header-icons']/button[2]")
	public WebElement descendAccountNumBtn;

}
