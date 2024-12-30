package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchPageTest extends BaseTest {
	
	@BeforeClass
	public void searchPageSetUp() {
		accPage=loginpage.dologin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	/*@DataProvider
	public Object[][] getProductData(){
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Apple"}
		};
	}
	
	
	@Test(dataProvider = "getProductData")
	public void searchkeyTest(String searchkey) {
	   searchpage=accPage.performSearch(searchkey);
	   String actsearchvalue=searchpage.getsearchvalue();
	   String actsearchheader=searchpage.getsearchheader();
	   //int actimgcount=searchpage.searchImagecount();
	   List<String> actprodtext=searchpage.searchprodtext();
	   Assert.assertTrue(searchpage.searchImagecount()>0);
	   
	}*/
	
	@DataProvider
	public Object[][] getProductTestData(){
		return new Object[][] {
			{"Macbook","MacBook Air"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""}
		};
	}
	
	@Test(dataProvider = "getProductTestData")
	public void selectproductTest(String searchkey,String productName) {
		searchpage=accPage.performSearch(searchkey);
		if(searchpage.searchImagecount()>0) {
			productInfoPage=searchpage.selectproduct(productName);
			String actualprodheader=productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actualprodheader, productName);
			
		}
	}
	
	
	/*@Test
	public void bsearchvalueTest() {
		String actsearchvalue=searchpage.getsearchvalue();
		Assert.assertEquals(actsearchvalue, "Macbook");
	}
	
	@Test
	public void csearchhederTest() {
		String actsearchheader=searchpage.getsearchheader();
		Assert.assertEquals(actsearchheader, "Search - Macbook");
	}
	
	@Test
	public void dsearchimgcountTest() {
		int actimgcount=searchpage.searchImagecount();
		Assert.assertEquals(actimgcount, 3);
		
	}
	
	@Test
	public void esearchprodtextTest() {
		List<String> actprodtext=searchpage.searchprodtext();
		Assert.assertTrue(actprodtext.contains("MacBook"));
	}
	
	@Test
	public void fselectproductTest() {
		searchpage.selectproduct("MacBook Air");
	}*/
	

}
