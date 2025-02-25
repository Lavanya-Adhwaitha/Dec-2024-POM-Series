package com.qa.opencart.factory;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.classfile.instruction.SwitchCase;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
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
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome");
				
			}
			else {
			t1driver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		}
	
		else if(browserName.equalsIgnoreCase("edge")) {
			//driver=new EdgeDriver(optionsManager.getEdgeOptions());
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("edge");
				
			}
			
		else{
			t1driver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		}
		}
		
		else if(browserName.equalsIgnoreCase("firefox")) {
			//driver=new FirefoxDriver(optionsManager.getFireOptions());
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("firefox");
				
			}
		else{
			t1driver.set(new FirefoxDriver(optionsManager.getFireOptions()));
		}
		}
		else {
			System.out.println("Please pass the right browser: " +browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	
	private void init_remoteDriver(String browser) {
		
		System.out.println("Runung test on grid serve:::" +browser);
		try {
		
		switch (browser.toLowerCase()) {
		case "chrome":
			t1driver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
		    break;
			
		case "firefox":
			t1driver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFireOptions()));
		    break;
			
		case "edge":
			t1driver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
			break;

		default:
			System.out.println("Please pass the right browser for execution: " +browser);
		}	
		}
		catch (MalformedURLException e) {
				e.printStackTrace();
			}
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
	
	
	 public static String getScreenshot() {
		  File srcfile= ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		   String path=System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		   File destination= new File(path);
		   try {
			FileHandler.copy(srcfile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return path;
	   }

	

}
