package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.util.ElementUtil;

public class SearchPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	private By searchbox=By.id("input-search");
	private By searchheader=By.tagName("h1");
	private By searchImage=By.xpath("(//div[@class='row'])[5]/div/div/div[@class='image']");
	private By searchprodtext=By.xpath("(//div[@class='row'])[5]/div/div/div/div/h4/a");

	public String getsearchvalue() {
		String serachvalue =eleUtil.getElementAttribute(searchbox, "value");
		System.out.println("The search value is : "+serachvalue);
		return serachvalue;
	}
	
	public String getsearchheader() {
		String searchheadertext=eleUtil.doElementGetText(searchheader);
		System.out.println("The search header data is : " +searchheadertext );
		return searchheadertext;
	}
	
	public int searchImagecount() {
	List<WebElement>imagecount=eleUtil.waitForElementsVisible(searchImage, AppConstant.DEFAULT_MEDIUM_TIME_OUT);
	int imagecountdata=imagecount.size();
	System.out.println("The Image count is : " +imagecountdata);
	return imagecountdata;
	}
	
	public List<String> searchprodtext() {
	List<WebElement>prodtext=eleUtil.waitForElementsVisible(searchprodtext, AppConstant.DEFAULT_MEDIUM_TIME_OUT);
	List<String> prodtextdata=new ArrayList<String>();
	for(WebElement e:prodtext) {
		String text=e.getText();
		System.out.println("The product text is : "+text);
		prodtextdata.add(text);
	}
	return prodtextdata;
	}
	
	public ProductInfoPage selectproduct(String productname) {
		By productlocator=By.linkText(productname);
		eleUtil.waitForElementVisible(productlocator, AppConstant.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
		
	}
	
	
}
