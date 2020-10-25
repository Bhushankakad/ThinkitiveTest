package com.thinkitive.ThinkitiveTest.utilities;

import java.awt.Desktop.Action;
import java.awt.RenderingHints.Key;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Utilities {
	

	FileOutputStream fos;
	BufferedWriter bf=null;

	public void click(WebElement ele1) {
		ele1.click();
	}
	
	public void clickAndSendKeys(WebElement ele1,String key) {
		ele1.click();
		ele1.sendKeys(key);
	}

	// Verify That Correct error message displays on window
	public void verifyErrorMessage(WebElement ele, String message) {
		String errorMessage = ele.getText();
		Assert.assertEquals(errorMessage, message);
	}

	public void verifySuccessMessage(WebElement ele, String message) {
		String successMessage = ele.getText();
		// Assert.assertEquals(successMessage, message);
		Assert.assertTrue(successMessage.contains(message), "Login is Successful");
	}

	public void selectElementFromDropdown(WebDriver driver,WebElement ele, String elementToBeSelectable, String message) {
		click(ele);
		Select selectElementFromDropdown = new Select(ele);
		//selectElementFromDropdown.selectByValue(elementToBeSelectable);
		selectElementFromDropdown.selectByVisibleText(elementToBeSelectable);
		waitTillElementToBe(driver, ele);
	}

	public void waitTillElementToBe(WebDriver driver, WebElement ele) {

		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfAllElements(ele));

	}

	public void waitTill(WebDriver driver, WebElement ele) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	
	public void closeCurrentWindow(WebDriver driver) {
		driver.close();
	}
	
	public void verifyThatProiseIsPresentInList(WebElement ele, String message) {
		String successMessage = ele.getText();
		// Assert.assertEquals(successMessage, message);
		Assert.assertTrue(successMessage.contains(message), "This is a Prmoise Test for testing purpose");
	}
	
	public void pressEnterKey(WebDriver driver) {
		Actions act=new Actions(driver);
		act.sendKeys(Keys.ENTER);
	}
	
	//write the output in file
	
	public void writeDataInFile(String path,WebDriver driver) {

		try {
			fos = new FileOutputStream(path);
			bf=new BufferedWriter(new OutputStreamWriter(fos));
			try {
				bf.write("Name Of Product");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//list all the elements
			List<WebElement> listOfProducts=driver.findElements(By.xpath("//div[@id='maindiv']/div/div/div/div[2]/a"));
			System.out.println(listOfProducts.size());
			for (int i=0;i<listOfProducts.size();i++){
				String productname=listOfProducts.get(i).getAttribute("title");
				try {
					bf.write(productname);
					bf.newLine();
					bf.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


}
