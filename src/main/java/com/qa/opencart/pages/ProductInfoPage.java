package com.qa.opencart.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String,String> productInfoMap;

	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	private By productheader=By.tagName("h1");
	private By productImage=By.xpath("//ul[@class='thumbnails']//img");
	private By ProductMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]//li");
	private By ProductPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]//li");
	private By Productcheckbox=By.xpath("//input[@type='checkbox' and @value='10']");
	private By ProductTest=By.xpath("//input[@id='input-option208']");
	private By Productselect=By.id("input-option217");
	private By ProductTextArea=By.id("input-option209");
	private By Uploadfile=By.xpath("//button[@id='button-upload222']");
	private By Dateclick=By.xpath("//div[@class='input-group date']/span//button");
	private By Dateselect=By.xpath("(//div[@class='datepicker-days'])[1]//tr/td[text()='24']");
	private By Timeclick=By.xpath("//div[@class='input-group time']//button");
	private By Timedecrease=By.xpath("(//div[@class='timepicker-picker'])[2]//td/a[@data-action='decrementHours']");
	private By TimedecMinutes=By.xpath("(//div[@class='timepicker-picker'])[2]//td/a[@data-action='decrementMinutes']");
	private By DateTime=By.xpath("//input[@id='input-option220']");
	private By DateTimeClick=By.xpath("//div[@class='input-group datetime']/span//button");
	private By DateTimeSelect=By.xpath("(//div[@class='datepicker-days'])[2]//tr/td[text()='25']");
	private By quality=By.xpath("//input[@id='input-quantity']");
	private By AddToCart=By.xpath("//button[@id='button-cart']");
	private By cartsucess=By.xpath("//div[@class='alert alert-success alert-dismissible']");
	private By writeareview=By.linkText("Write a review");
	private By name=By.id("input-name");
	private By reviewtext=By.id("input-review");
	private By ratingradiobtn=By.xpath("//input[@value='4']");
	private By continbtn=By.id("button-review");
	private By alertClose=By.xpath("//button[@class='close']");
	
			
		public String getProductHeaderValue() {
		String productheader1=eleUtil.doElementGetText(productheader);
		return productheader1;
	}
	
	public int getProductImage() {
		int productImagecount=eleUtil.waitForElementsVisible(productImage, AppConstant.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("The product image count is : " +productImagecount);
		return  productImagecount;
	}
	
	public Map<String, String> getProductInfo() {
		productInfoMap=new LinkedHashMap<String, String>();
		productInfoMap.put("productname", getProductHeaderValue());
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
		
	}
	
	private void getProductMetaData() {
		List<WebElement> metalist=eleUtil.getElements(ProductMetaData);
		for(WebElement e:metalist) {
			String meta=e.getText();
			String metaInfo[]=meta.split(":");
			String key=metaInfo[0].trim();
			String value=metaInfo[1].trim();
			productInfoMap.put(key, value);
		}
	}
	
	private void getProductPriceData() {
		List<WebElement> priceList=eleUtil.getElements(ProductPriceData);
		String price=priceList.get(0).getText();
		
		String exTax=priceList.get(2).getText();
		String  Tax[]=exTax.split(":");
		String ExTax=Tax[0].trim();
		String val=Tax[1].trim();
		
		
		productInfoMap.put("productstrickprrice",price);
		
		productInfoMap.put(ExTax, val);
		
		
	}
	
	public void getProductchckboxselect() {
		eleUtil.clickWhenReady(AppConstant.DEFAULT_MEDIUM_TIME_OUT, Productcheckbox);
	}
	public void getProductTest(String test) {
		eleUtil.doSendKeys(ProductTest, test);
	}
	
	public void productSelectdropdown(String value) {
		eleUtil.doSelectDropDownByValue(Productselect, value);
	}
	
	public void productTextarea(String text) {
		eleUtil.doSendKeys(ProductTextArea, text);
	}
	
	public void Prodcutfile(String filedata) throws InterruptedException, AWTException{
		Thread.sleep(1000);
		driver.findElement(Uploadfile).click();
		Thread.sleep(3000);
		StringSelection selection=new StringSelection(filedata);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(4000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public String fileAlert() {
		String alerttext=eleUtil.getAlertText(AppConstant.DEFAULT_LONG_TIME_OUT);
		System.out.println("The alert is : " +alerttext);
		return alerttext;
	}
	
	public void fileAlertaccept() {
		eleUtil.acceptAlert(AppConstant.DEFAULT_LONG_TIME_OUT);
	}
	
	public void productdateclick() {
		eleUtil.clickWhenReady(AppConstant.DEFAULT_MEDIUM_TIME_OUT, Dateclick);
		eleUtil.clickWhenReady(AppConstant.DEFAULT_LONG_TIME_OUT, Dateselect);
	}
	
	public void producTimeclick() {
		eleUtil.clickWhenReady(AppConstant.DEFAULT_LONG_TIME_OUT, Timeclick);
		eleUtil.clickWhenReady(AppConstant.DEFAULT_LONG_TIME_OUT, Timedecrease);
		eleUtil.clickWhenReady(AppConstant.DEFAULT_LONG_TIME_OUT, TimedecMinutes);
		eleUtil.clickWhenReady(AppConstant.DEFAULT_MEDIUM_TIME_OUT, DateTime);
	}
	
	public void productDateTime() {
		eleUtil.clickWhenReady(AppConstant.DEFAULT_LONG_TIME_OUT, DateTimeClick);
		eleUtil.clickWhenReady(AppConstant.DEFAULT_LONG_TIME_OUT, DateTimeSelect);
	}
	
	public void productQuantity(String value) {
		eleUtil.doSendKeys(quality,value);
	}
	
	public String productAddToCart() throws InterruptedException {
		eleUtil.clickWhenReady(AppConstant.DEFAULT_LONG_TIME_OUT, AddToCart);
		Thread.sleep(3000);
		String successmsg=eleUtil.waitForElementVisible(cartsucess,AppConstant.DEFAULT_LONG_TIME_OUT).getText();
		successmsg=successmsg.substring(0,successmsg.length()-1).replace("x", "");
		System.out.println("Cart message is : " +successmsg);
		return successmsg;
	}
	
	public void productAlertClose() {
		eleUtil.clickWhenReady(AppConstant.DEFAULT_LONG_TIME_OUT, alertClose);
	}
	
	
	
	public void productReview(String value,String value1) {
		eleUtil.clickWhenReady(AppConstant.DEFAULT_LONG_TIME_OUT, writeareview);
		eleUtil.doSendKeys(name, value);
		eleUtil.doSendKeys(reviewtext, value1);
		eleUtil.clickWhenReady(AppConstant.DEFAULT_LONG_TIME_OUT, ratingradiobtn);
		eleUtil.clickWhenReady(AppConstant.DEFAULT_LONG_TIME_OUT, continbtn);
	}
	
	
	

}
