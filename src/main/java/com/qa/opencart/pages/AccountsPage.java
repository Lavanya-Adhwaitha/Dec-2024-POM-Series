package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.util.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
	
	private By accheaders=By.xpath("//div[@id='content']/h2");
	private By searchbox=By.xpath("//input[@name='search']");
	private By Myacclist=By.xpath("((//div[@id='content']/h2[text()='My Orders']/..)//ul)[1]/li/a");
	private By Myorderlist=By.xpath("((//div[@id='content']/h2[text()='My Orders']/..)//ul)[2]/li/a");
	private By Affilateacclist=By.xpath("((//div[@id='content']/h2[text()='My Affiliate Account']/..)//ul)[3]/li/a");
	private By Newsletterlist=By.xpath("((//div[@id='content']/h2[text()='Newsletter']/..)//ul)[4]/li/a");
	private By toplist=By.xpath("//ul[@class='list-inline']//span[@class='hidden-xs hidden-sm hidden-md']");
	private By Logout=By.xpath("//div[@class='list-group']//a[text()='Logout']");
	private By searchicon=By.xpath("//button[@type='button']/i[@class='fa fa-search']");
	
	
	public String getAccTitle() {
		//String Title=driver.getTitle();
		String Title=eleutil.waitForTitleContainsAndFetch(AppConstant.DEFAULT_MEDIUM_TIME_OUT, AppConstant.ACCOUNT_PAGE_TITLE_VALUE);
		System.out.println("The Account page Title contain  is : " +Title);
		return Title;
	}
	
	public String getAccURL() {
		//String currentURL= driver.getCurrentUrl();
		String currentURL=eleutil.waitForURLContainsAndFetch(AppConstant.DEFAULT_SHORT_TIME_OUT, AppConstant.ACCOUNT_PAGE_URL_FRACTION_VALUE);
		System.out.println("The Accounts Page URL is : "+currentURL);
		return currentURL;
	}
	
	
	
	public List<String> getAccHeaders() {
		//List<WebElement> accheaderdata=driver.findElements(accheaders);
		List<WebElement> accheaderdata=eleutil.waitForElementsVisible(accheaders, AppConstant.DEFAULT_MEDIUM_TIME_OUT);
		List<String> accheader=new ArrayList<String>();
		for(WebElement e:accheaderdata) {
			String text=e.getText();
			System.out.println("The accounts header list is " +text);
			accheader.add(text);
		}
		return accheader;
	}
	
	public boolean getSearchicon() {
		//return driver.findElement(searchbox).isDisplayed();
		return eleutil.waitForElementVisible(searchbox, AppConstant.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	
	public boolean isLogoutLinkExist() {
		
		return eleutil.waitForElementVisible(Logout, AppConstant.DEFAULT_LONG_TIME_OUT).isDisplayed();
	}
	
	public List<String> getMyAccountList() {
		//List<WebElement> Myaccountlistdata=driver.findElements(Myacclist);
		List<WebElement> Myaccountlistdata=eleutil.waitForElementsVisible(Myacclist, AppConstant.DEFAULT_MEDIUM_TIME_OUT);
		List<String> Myaccdatalist=new ArrayList<String>();
		for(WebElement e:Myaccountlistdata) {
			String text=e.getText();
			System.out.println("The My accounts list is " +text);
			Myaccdatalist.add(text);
		}
		return Myaccdatalist;
	}
	
	public List<String> getMyOrderList() {
		//List<WebElement> Myorder=driver.findElements(Myorderlist);
		List<WebElement> Myorder=eleutil.waitForElementsVisible(Myorderlist, AppConstant.DEFAULT_SHORT_TIME_OUT);
		List<String> Myorderdata=new ArrayList<String>();
		for(WebElement e:Myorder) {
			String text=e.getText();
			System.out.println("The My order list is " +text);
			Myorderdata.add(text);
		}
		return Myorderdata;
	}
	
	public List<String> getAffilateList() {
		//List<WebElement> Affilatedata=driver.findElements(Affilateacclist);
		List<WebElement> Affilatedata=eleutil.waitForElementsVisible(Affilateacclist, AppConstant.DEFAULT_SHORT_TIME_OUT);
		List<String> Affilatedatalist=new ArrayList<String>();
		for(WebElement e:Affilatedata) {
			String text=e.getText();
			System.out.println("The Affilate list is " +text);
			Affilatedatalist.add(text);
		}
		return Affilatedatalist;
	}
	
	public List<String> getNewsletter() {
		//List<WebElement> Newsletter=driver.findElements(Newsletterlist);
		List<WebElement> Newsletter=eleutil.waitForElementsVisible(Newsletterlist, AppConstant.DEFAULT_MEDIUM_TIME_OUT);
		List<String> Newsletterdata=new ArrayList<String>();
		for(WebElement e:Newsletter) {
			String text=e.getText();
			System.out.println("The Newsletter list is " +text);
			Newsletterdata.add(text);
		}
		return Newsletterdata;
	}
	
	public List<String> gettoplist() {
		//List<WebElement> toplistvalue=driver.findElements(toplist);
		List<WebElement> toplistvalue=eleutil.waitForElementsVisible(toplist, AppConstant.DEFAULT_SHORT_TIME_OUT);
		List<String> toplistdata=new ArrayList<String>();
		for(WebElement e:toplistvalue) {
			String text=e.getText();
			System.out.println("The Top list is " +text);
			toplistdata.add(text);
		}
		return toplistdata;
	}
	
	public SearchPage performSearch(String searchkey) {
		if(getSearchicon()) {
			eleutil.doSendKeys(searchbox, searchkey);
			eleutil.doClick(searchicon);
			return new SearchPage(driver);
		}
		else {
			System.out.println("The search field is not present");
			return null;
		}
		
	}

	
	
	

}
