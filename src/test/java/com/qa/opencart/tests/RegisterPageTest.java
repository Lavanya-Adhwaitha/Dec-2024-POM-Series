package com.qa.opencart.tests;

import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.util.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	
	@BeforeClass
	public void regPageSetup() {
		registerpage=loginpage.registerclick();
	}
	
	public String getRandomEmail() {
		//Random random=new Random();
		//String email="automation"+random.nextInt(1000)+"@gmail.com";
		String email="automation"+System.currentTimeMillis()+"@gmail.com";
		return email;
	}
	
	
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object regdata[][]=ExcelUtil.getTestData(AppConstant.REGISTER_SHEET_NAME);
		return regdata;
	}
	
	@Test(dataProvider = "getRegTestData" )
	public void userRegTest(String firstname,String lastname,String telephone,String password,String subscribe) {
		registerpage.registerUser(firstname, lastname, getRandomEmail(), telephone, password, subscribe);
	}

}
