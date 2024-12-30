package com.qa.opencart.factory;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.io.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> t1driver=new ThreadLocal<WebDriver>();
	
	
	public WebDriver initDriver(Properties prop) {
		
		optionsManager=new OptionsManager(prop);
		highlight=prop.getProperty("highlight");
	    String browserName = prop.getProperty("browser").toLowerCase().trim();
		
	    System.out.println("The browser name is the the " +browserName);
		
		if(browserName.equalsIgnoreCase("chrome")){
			//driver=new ChromeDriver(optionsManager.getChromeOptions());
			t1driver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			//driver=new EdgeDriver(optionsManager.getEdgeOptions());
			t1driver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			//driver=new FirefoxDriver(optionsManager.getFireOptions());
			t1driver.set(new FirefoxDriver(optionsManager.getFireOptions()));
		}
		else {
			System.out.println("Please pass the right browser: " +browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public synchronized static WebDriver getDriver() {
		return t1driver.get();
	}
	
	public Properties initProp() {
		
		prop = new Properties();
		FileInputStream ip = null;
		
		String envName=System.getProperty("env");
		System.out.println("Running testcases on Env: " + envName);
		
			try {
				if(envName==null) {
					System.out.println("no env is passed.. Running testcases on QA Env..");
				    ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} 
				else {
					switch (envName.toLowerCase().trim()) {
					case "qa":
						 ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
						 break;
					case "dev":
						ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
						 break;
					case "stage":
						ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
						 break;
					case "prod":
						ip = new FileInputStream("./src/test/resources/config/config.properties");
						 break;

					default:
						System.out.println("Wrong env is passed...No need to run the testcases");
						break;
					}
					
				}
			}	
				catch (FileNotFoundException e) {
				
			}
		    try {
				prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return prop;
	}
	
	
	public static String getScreenshot(String methodName) {
		File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination=new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		
	}
	

	

}
