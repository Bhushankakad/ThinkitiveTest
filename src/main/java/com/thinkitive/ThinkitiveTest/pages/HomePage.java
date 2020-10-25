package com.thinkitive.ThinkitiveTest.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinkitive.ThinkitiveTest.base.Base;

public class HomePage extends Base {
	
	@FindBy(xpath = "//input[@id='search_box']")
	public WebElement searchElement;
	
	@FindBy(xpath = "//div[@id='searchlist']/ul/li[1]")
	public WebElement selectElementFromDropdown;
	
	@FindBy(xpath="//div[@id='maindiv']/div/div/div/div[3]/span[1]")
	public WebElement priceOfProduct;
	
	//*[@id='maindiv']//div/a/img
	
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

}
