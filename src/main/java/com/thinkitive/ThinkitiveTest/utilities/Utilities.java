package com.thinkitive.ThinkitiveTest.utilities;

import java.awt.Desktop.Action;
import java.awt.RenderingHints.Key;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Utilities {

	FileOutputStream fos;
	BufferedWriter bf = null;

	public void click(WebElement ele1) {
		ele1.click();
	}

	public void clickAndSendKeys(WebElement ele1, String key) {
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

	public void selectElementFromDropdown(WebDriver driver, WebElement ele, String elementToBeSelectable,
			String message) {
		click(ele);
		Select selectElementFromDropdown = new Select(ele);
		// selectElementFromDropdown.selectByValue(elementToBeSelectable);
		selectElementFromDropdown.selectByVisibleText(elementToBeSelectable);
		waitTillElementToBe(driver, ele);
	}

	public void waitTillElementToBe(WebDriver driver, WebElement ele) {

		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfAllElements(ele));

	}

	public void waitTillPageLoad(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("div")));
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
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER);
	}

	// write the output in file

	public void writeDataInFile(String path, WebDriver driver, String nameOfProduct, Float priceOfProduct,
			String discountOfProductInPercentage, String originalProductPrice) {

		try {
			fos = new FileOutputStream(path);
			bf = new BufferedWriter(new OutputStreamWriter(fos));

			try {
				bf.write("Product Name having highest price is :" + nameOfProduct);
				bf.newLine();
				bf.write("Price of the Product is  :" + priceOfProduct);
				bf.newLine();
				bf.write("Original Price of product is  :" + originalProductPrice);
				bf.newLine();
				bf.write("Discount on product in Percentage having highest price is :" + discountOfProductInPercentage);
				bf.newLine();
				bf.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/*
	 * This utility with interate over the products and store the info in TreeMap
	 * with price as the Key and other info in List as a value of it
	 * 
	 * 
	 */

	public TreeMap<Float, List<String>> getProductOfHighestPrice(WebDriver driver) {
		List<WebElement> listOfprice = driver.findElements(By.xpath("//div[@id='maindiv']/div/div/div/div[3]/span[1]"));
		int sizeOfList = listOfprice.size();
		TreeMap<Float, List<String>> map = new TreeMap<Float, List<String>>();
		List<String> listOfFloat = null;
		for (int i = 1; i <= sizeOfList; i++) {
			listOfFloat = new ArrayList<String>();
			//Get Product Name
			String productName = driver.findElement(By.xpath("//*[@id=\"maindiv\"]/div[" + i + "]/div/div[1]/div[2]/a"))
					.getText();
			
			//Get price of product
			String price = driver
					.findElement(By.xpath("//*[@id=\"maindiv\"]/div[" + i + "]/div/div[1]/div[3]/span[1]/a")).getText();
			Float price1 = Float.parseFloat(price);
			listOfFloat.add(productName);
			/* All the Product doesnt have price in decimal format */
			if (price.contains(".")) {
				String originalPrice = driver
						.findElement(By.xpath("//*[@id=\"maindiv\"]/div[" + i + "]/div/div[1]/div[3]/span[2]/a"))
						.getText();
				String offVale = driver
						.findElement(By.xpath("//*[@id=\"maindiv\"]/div[" + i + "]/div/div[1]/div[3]/span[3]/a"))
						.getText();
				listOfFloat.add(originalPrice);
				listOfFloat.add(offVale);
			}
			map.put(price1, listOfFloat);

		}

		return map;
	}

}
