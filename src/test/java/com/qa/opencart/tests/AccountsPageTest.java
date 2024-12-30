package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetUp() {
		accPage=loginpage.dologin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	@Test(priority = 1)
	public void accPageTitleTest() {
		String actualTitle=accPage.getAccTitle();
		Assert.assertEquals(actualTitle, AppConstant.ACCOUNT_PAGE_TITLE_VALUE);
	}
	
	@Test(priority = 2)
	public void accPageURLTest() {
		String actualURL=accPage.getAccURL();
		Assert.assertTrue(actualURL.contains(AppConstant.ACCOUNT_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test(priority = 3)
	public void toplistTest() {
		List<String> actualtoplist=accPage.gettoplist();
		Assert.assertEquals(actualtoplist.size(), 5);
	}
	
	@Test(priority = 4)
	public void searchiconTest() {
		assertTrue(accPage.getSearchicon());
	}
	
	@Test(priority = 5)
	public void MyaccheardsTest() {
		List<String> actualaccheaderlist=accPage.getAccHeaders();
		Assert.assertTrue(actualaccheaderlist.contains("My Account"));
	}
	
	@Test(priority = 6)
	public void MyaccListTest() {
		List<String> actualacclist=accPage.getMyAccountList();
		Assert.assertTrue(actualacclist.contains("Edit your account information"));
	}
	
	@Test(priority = 7)
	public void MyorderTest() {
		List<String> actualorderlist=accPage.getMyOrderList();
		Assert.assertEquals(actualorderlist.size(), 6);
	}
	
	@Test(priority = 8)
	public void AfflicateTest() {
		List<String> actualaffilatelist=accPage.getAffilateList();
		Assert.assertEquals(actualaffilatelist.size(), 1);
	}
	
	@Test(priority = 9)
	public void NewLetterTest() {
		List<String> actualNewlist=accPage.getNewsletter();
		Assert.assertEquals(actualNewlist.size(), 1);
	}
	
	@Test(priority = 10)
    public void LogoutTest() {
    	assertTrue(accPage.isLogoutLinkExist());
    }
	
	@Test(priority = 11)
	public void searchkeyTest() {
	   searchpage=accPage.performSearch("Macbook");
	   Assert.assertTrue(searchpage.searchImagecount()>0);
	}


}
