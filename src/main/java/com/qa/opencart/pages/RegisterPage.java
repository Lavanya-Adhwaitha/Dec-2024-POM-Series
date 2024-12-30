package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.util.ElementUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	private By firstname = By.id("input-firstname");
	private By lastname=By.id("input-lastname");
	private By email=By.id("input-email");
	private By telephone=By.id("input-telephone");
	private By password=By.id("input-password");
	private By confirmpwd=By.id("input-confirm");
	private By yessubscribe=By.xpath("(//input[@type='radio'])[2]");
	private By nosubscribe=By.xpath("(//input[@type='radio'])[3]");
	private By privacypolicy=By.xpath("//input[@name='agree']");
	private By continu=By.xpath("//input[@value='Continue']");
	private By successmsg=By.xpath("//div[@id='content']/h1");
	private By succontin=By.xpath("//div[@class='pull-right']/a");
	private By logout=By.linkText("Logout");
	private By register=By.linkText("Register");
	
	
	public void registerUser(String firstname,String lastname,String email,String telephone,String password,String subscribe) {
		eleUtil.waitForElementVisible(this.firstname, AppConstant.DEFAULT_LONG_TIME_OUT).sendKeys(firstname);
		eleUtil.doSendKeys(this.lastname, lastname);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpwd, password);
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(yessubscribe);
			
		}
		else {
			eleUtil.doClick(nosubscribe);
		}
		eleUtil.doClick(privacypolicy);
		eleUtil.doClick(continu);
		String messagesucess=eleUtil.waitForElementVisible(successmsg,AppConstant.DEFAULT_MEDIUM_TIME_OUT).getText();
		System.out.println("The message is : " +messagesucess);
		eleUtil.clickWhenReady(AppConstant.DEFAULT_LONG_TIME_OUT, succontin);
		eleUtil.clickWhenReady(AppConstant.DEFAULT_MEDIUM_TIME_OUT, logout);
		eleUtil.clickWhenReady(AppConstant.DEFAULT_LONG_TIME_OUT, register);
		
		
		
		
	}
}
