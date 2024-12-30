package com.qa.opencart.tests;

import java.awt.AWTException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void searchPageSetUp() {
		accPage=loginpage.dologin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	
	/*@DataProvider
	public Object[][] getProductImageTestData(){
		return new Object[][] {
			{"Macbook","MacBook Air",4},
			{"iMac","iMac",3},
			{"Apple","Apple Cinema 30\"",6}
		};
		}
	
	@Test(dataProvider = "getProductImageTestData")
	public void getProductImageCountTest(String searchdata,String productname,int imagecount) {
		searchpage=accPage.performSearch(searchdata);
		productInfoPage=searchpage.selectproduct(productname);
		int actualProductImageCount=productInfoPage.getProductImage();
		Assert.assertEquals(actualProductImageCount, imagecount);
	}
	
	@Test
	public void productInfoTest() {
		searchpage=accPage.performSearch("Apple");
		productInfoPage=searchpage.selectproduct("Apple Cinema 30\"");
		Map<String, String> actualProductInfoMap=productInfoPage.getProductInfo();
		System.out.println(actualProductInfoMap);
		softAssert.assertEquals(actualProductInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(actualProductInfoMap.get("Product Code"),"Product 15");
		softAssert.assertEquals(actualProductInfoMap.get("Reward Points"), "100");
		
		softAssert.assertAll();
	
	}
	
	@Test
	public void productdataTest() throws InterruptedException, AWTException {
		searchpage=accPage.performSearch("Apple");
		productInfoPage=searchpage.selectproduct("Apple Cinema 30\"");
		productInfoPage.getProductHeaderValue();
		productInfoPage.getProductImage();
		productInfoPage.getProductchckboxselect();
		productInfoPage.getProductTest("Testing data");
		productInfoPage.productSelectdropdown("1");
		productInfoPage.productTextarea("Lavanya");
		productInfoPage.Prodcutfile("C:\\Users\\lavan\\OneDrive\\Pictures\\Saved Pictures\\download.jpg");
		productInfoPage.fileAlert();
		productInfoPage.fileAlertaccept();
		productInfoPage.productdateclick();
		productInfoPage.producTimeclick();
		productInfoPage.productDateTime();
		productInfoPage.productQuantity("3");
		productInfoPage.productAddToCart();
		
	}*/
	
	@Test
	public void productdataTest1() throws InterruptedException{
		searchpage=accPage.performSearch("iMac");
		productInfoPage=searchpage.selectproduct("iMac");
		productInfoPage.getProductHeaderValue();
		productInfoPage.getProductImage();
		productInfoPage.productQuantity("3");
		productInfoPage.productReview("Test", "Just when you thought iMac had everything, now there´s even more. More powerful Intel Core 2 Duo processors. And more memory standard. Combine this with Mac OS X Leopard and iLife ´08, and it´s more all-in-one than ever. iMac packs amazing performance into a stunningly slim space.");
		productInfoPage.productAddToCart();
		productInfoPage.productAlertClose();
		
		
	}
	
	
	
	
	

}
