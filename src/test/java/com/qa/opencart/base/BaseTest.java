package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchPage;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginpage;
	protected AccountsPage accPage;
	protected SearchPage searchpage;
	protected ProductInfoPage  productInfoPage;
	protected SoftAssert softAssert;
	protected RegisterPage registerpage;
	
	@BeforeTest
    public void setup() {
	df=new DriverFactory();
	prop=df.initProp();
	driver=df.initDriver(prop);
	loginpage=new LoginPage(driver);
	softAssert=new SoftAssert();
    }
	
	
	@AfterTest
	public void teardown() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
		
	}

}
