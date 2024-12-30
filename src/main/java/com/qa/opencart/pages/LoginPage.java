package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
	
	
	private By emailid=By.id("input-email");
	private By password=By.id("input-password");
	private By forgottonpwd=By.linkText("Forgotten Password");
	private By login=By.xpath("//input[@value='Login']");
	private By Newcustomer=By.xpath("//h2[text()='New Customer']");
	private By pagelist=By.xpath("//div[@class='list-group']/a");
	private By informationlist=By.xpath("//h5[text()='Information']/../ul/li");
	private By Cusservicelist=By.xpath("//h5[text()='Customer Service']/../ul/li");
	private By Extras=By.xpath("//h5[text()='Extras']/../ul/li");
	private By Myacctontlist=By.xpath("//h5[text()='My Account']/../ul/li");
	private By footertext=By.xpath("//p/a[contains(text(),'OpenCart')]");
	private By navbarlist=By.xpath("//ul[@class='nav navbar-nav']/li/a");
	private By register=By.linkText("Register");
	
	
	@Step("...getting the login page title")
	public String getLoginTitle() {
		String Title=driver.getTitle();
		System.out.println("The Login page Title is: " +Title);
		return Title;
	}
	
	@Step("...getting the login page URL")
	public String getPageURL() {
		String currentURL= driver.getCurrentUrl();
		System.out.println("The Login Page URL is : "+currentURL);
		return currentURL;
	}
	
	@Step("...getting the login page forgotpwd")
	public boolean getForgottonPwd() {
		//return driver.findElement(forgottonpwd).isDisplayed();
		return eleutil.waitForElementVisible(forgottonpwd, 10).isDisplayed();
	}
	
	@Step("...getting the login page customertext")
	public String getNewCustomText() {
		//String CustomerText=driver.findElement(Newcustomer).getText();
		String CustomerText=eleutil.doElementGetText(Newcustomer);
		System.out.println("The page Text is : " +CustomerText);
		return CustomerText;
	}
	
	@Step("...getting the login page getPageList")
	public List<String> getPageList() {
		List<WebElement> pagelistdata=driver.findElements(pagelist);
		List<String> pagelist1=new ArrayList<String>();
		for(WebElement e:pagelistdata) {
			String text=e.getText();
			System.out.println("The page list is " +text);
			pagelist1.add(text);
		}
		return pagelist1;
	}
	
	
	@Step("...getting the login page getInformationList")
	public List<String> getInformationList() {
		List<WebElement> informationdata=driver.findElements(informationlist);
		List<String> informationlist=new ArrayList<String>();
		for(WebElement e:informationdata) {
			String text=e.getText();
			System.out.println("The information list is " +text);
			informationlist.add(text);
		}
		return informationlist;
	}
	
	
	@Step("...getting the login page getCustomerList")
	public List<String> getCustomerList() {
		//List<WebElement> Customerdata=driver.findElements(Cusservicelist);
		List<WebElement> Customerdata=eleutil.waitForElementsVisible(Cusservicelist, 20);
		List<String> Customerlist=new ArrayList<String>();
		for(WebElement e:Customerdata) {
			String text=e.getText();
			System.out.println("The Cusservice list is " +text);
			Customerlist.add(text);
		}
		return Customerlist;
	}
	
	
	@Step("...getting the login page getExtras")
	public List<String> getExtras() {
		//List<WebElement> Extarsdata=driver.findElements(Extras);
		List<WebElement> Extarsdata=eleutil.waitForElementsVisible(Extras, 10);
		List<String> Extraslist=new ArrayList<String>();
		for(WebElement e:Extarsdata) {
			String text=e.getText();
			System.out.println("The Extras list is " +text);
			Extraslist.add(text);
		}
		return Extraslist;
	}
	
	
	@Step("...getting the login page GetMyAccount")
	public List<String> getMyAccount() {
		//List<WebElement> MyAccdaata=driver.findElements(Myacctontlist);
		List<WebElement> MyAccdata=eleutil.waitForElementsVisible(Myacctontlist, 10);
		List<String> Myacclist=new ArrayList<String>();
		for(WebElement e:MyAccdata) {
			String text=e.getText();
			System.out.println("The Myaccount list is " +text);
			Myacclist.add(text);
		}
		return Myacclist;
	}
	
	
	@Step("...getting the login page footerText")
	public String getfooterText() throws InterruptedException {
		Thread.sleep(1000);
		//String FooterText=driver.findElement(footertext).getText();
		String FooterText=eleutil.doElementGetText(footertext);
		System.out.println("The page Text is : " +FooterText);
		return FooterText;
	}
	
	@Step("...getting the login page Navigationbar")
	public List<String> getnavbar() {
		//List<WebElement> navbardata=driver.findElements(navbarlist);
		List<WebElement> navbardata=eleutil.waitForElementsVisible(navbarlist, 20);
		List<String> navbarlist=new ArrayList<String>();
		for(WebElement e:navbardata) {
			String text=e.getText();
			System.out.println("The information list is " +text);
			navbarlist.add(text);
		}
		return navbarlist;
	}
	
	@Step("Login with username : {0} and Passowrd : {1}")
	public AccountsPage dologin(String UN, String Pwd) {
		//driver.findElement(emailid).sendKeys(UN);
		eleutil.waitForElementVisible(emailid, 10).sendKeys(UN);
		eleutil.doSendKeys(password, Pwd);
		eleutil.doClick(login);
		return new AccountsPage(driver);
	}
	
	@Step("...navigating to the register page")
	public RegisterPage registerclick() {
		eleutil.clickWhenReady(AppConstant.DEFAULT_LONG_TIME_OUT, register);
		return new RegisterPage(driver);
	}
	
	
	
	

}
