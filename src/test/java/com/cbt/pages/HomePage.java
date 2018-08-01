package com.cbt.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cbt.utilities.Driver;

public class HomePage {
	private WebDriver driver;

	public HomePage() {
		driver = Driver.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(text(),'Profiles')]")
	public WebElement profileMenu;

	@FindBy(linkText = "States")
	public WebElement profilesStateOption;

	@FindBy(xpath = "//ul[@class='nav-children__list']/li/a[.='Federal Accounts']")
	public WebElement profilesFederalAccountsOption;
	
	@FindBy(xpath = "//div[@id='logo']/a")
	public WebElement logo;

	@FindBy(xpath = "//ul[@class='full-menu__list']")
	public WebElement mainMenu;

}
