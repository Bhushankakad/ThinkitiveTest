package com.thinkitive.ThinkitiveTest.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	public WebDriver driver;
	String pathOfConfigFile=System.getProperty("user.dir")+"\\src\\main\\java\\com\\thinkitive\\ThinkitiveTest\\config\\config.properties";
	String pathforChromeDriver=System.getProperty("user.dir")+"\\src\\main\\java\\com\\thinkitive\\ThinkitiveTest\\driver\\chromedriver.exe";
	
	Properties prop=new Properties();
	
	public Base() {
		FileInputStream fis;
		try {
			fis = new FileInputStream(pathOfConfigFile);
			
			prop.load(fis);
			//pathforChromeDriver=System.getProperty("user.dir")+prop.getProperty("pathforChromediver");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
	
	
	public void init() {
		String url=prop.getProperty("url");
		System.setProperty("webdriver.chrome.driver",pathforChromeDriver);
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
	}
	

}
