package com.cbt.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.cbt.utilities.ConfigurationReader;
import com.cbt.utilities.Driver;

public abstract class TestBase {

	
	protected WebDriver driver;
	
	
	@Parameters("browser") //name of the parameter from xml
	@BeforeMethod (groups= {"smoke"})
	public void setUp(@Optional String browser) {//name of the parameter from xml
		driver=Driver.getDriver(browser);
		driver.get(ConfigurationReader.getProperty("url"));
	}
	
	
	@AfterMethod (groups= {"smoke"})
	public void tearDown() {
		Driver.closeDriver();
	}
}
