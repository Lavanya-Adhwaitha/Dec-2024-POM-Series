package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC - 100: design login for open cart app")
@Story("US-Login: 101:design login page features for opencart")
public class LoginPageTest extends BaseTest {
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("...checking the title of the login page ")
	@Test(priority=1)
	public void pageTitleTest() {
		String actualtitle=loginpage.getLoginTitle();
		Assert.assertEquals(actualtitle, "Account Login");
	}
	
	@Severity(SeverityLevel.MINOR)
	@Description("...checking the URL of the login page ")
	@Test(priority = 2)
	public void pageURLTest() {
		String actualURL=loginpage.getPageURL();
		Assert.assertTrue(actualURL.contains("route=account/login"));
	}
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("...checking the forgot pwd of the login page ")
	@Test(priority = 3)
	public void forgotpwdTest() {
		Assert.assertTrue(loginpage.getForgottonPwd());
	}
	
	@Severity(SeverityLevel.MINOR)
	@Description("...checking the new customer of the login page ")
	@Test(priority = 4)
	public void newCustomerTest() {
		String actualcusttext=loginpage.getNewCustomText();
		Assert.assertTrue(actualcusttext.contains("Customer"));
	}
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("...checking the Navigationbar of the login page ")
	@Test(priority = 5)
	public void navigationbarTest() {
		List<String> actualnavbarlist=loginpage.getnavbar();
		Assert.assertTrue(actualnavbarlist.contains("Cameras"));
	}
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("...checking the Pagelist of the login page ")
	
	@Test(priority = 6)
	public void pageListTest() throws InterruptedException {
		List<String> actualPagelist=loginpage.getPageList();
		
		Assert.assertEquals(actualPagelist.size(),13);
	}
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("...checking the Informationlist of the login page ")
	@Test(priority = 7)
	public void InformationlistTest() {
		List<String> actInfolist=loginpage.getInformationList();
		Assert.assertTrue(actInfolist.contains("About Us"));
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("...checking the Customerlist of the login page ")
	@Test(priority = 8)
	public void CustomerlistTest() {
		List<String> actcuslist=loginpage.getCustomerList();
		Assert.assertTrue(actcuslist.contains("Returns"));
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("...checking the Extralist of the login page ")
	@Test(priority = 9)
	public void ExtrasslistTest() {
		List<String> actextraslist=loginpage.getExtras();
		Assert.assertTrue(actextraslist.contains("Affiliate"));
	}
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("...checking the Myacclist of the login page ")
    @Test(priority = 10)
	public void MyacclistTest() {
		List<String> actMyacclist=loginpage.getMyAccount();
		Assert.assertTrue(actMyacclist.contains("Newsletter"));
	}
	
	@Severity(SeverityLevel.MINOR)
	@Description("...checking the footerlist of the login page ")
	@Test(priority = 11)
	public void FooterTest() throws InterruptedException {
		String actualFootertext=loginpage.getfooterText();
		Assert.assertTrue(actualFootertext.contains("OpenCart"));
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("...checking the dologin of the login page ")
	@Test(priority = 12)
	public void dologin() {
		accPage=loginpage.dologin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	
	
	
	
	

}
