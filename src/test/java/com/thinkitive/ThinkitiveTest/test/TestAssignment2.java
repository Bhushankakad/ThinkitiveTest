package com.thinkitive.ThinkitiveTest.test;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thinkitive.ThinkitiveTest.base.Base;
import com.thinkitive.ThinkitiveTest.pages.HomePage;
import com.thinkitive.ThinkitiveTest.utilities.Utilities;

public class TestAssignment2 extends Base {
	HomePage homepage = null;
	Utilities utilities = new Utilities();

	@BeforeClass
	public void openBroser() {
		init();
		homepage = new HomePage(driver);

	}

	@Test
	public void testWriteProductInfoInTextFile() {
		// Step 1 :Open browser with link
		utilities.click(homepage.searchElement);

		// Step 2 :Search with search text
		homepage.searchElement.sendKeys("hoodies");
		
		utilities.waitTill(driver, homepage.selectElementFromDropdown);

		utilities.pressEnterKey(driver);

		utilities.click(homepage.selectElementFromDropdown);

		String pathOfProductfile = System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\thinkitive\\ThinkitiveTest\\testdata\\product.txt";

		// write the data in file
		utilities.writeDataInFile(pathOfProductfile, driver);

	}

	@Test(dependsOnMethods = { "testWriteProductInfoInTextFile" })
	public void getProductWithHighestPrice() {

		List<WebElement> listOfprice = driver.findElements(By.xpath("//div[@id='maindiv']/div/div/div/div[3]/span[1]"));
		int sizeOfList = listOfprice.size();
		Float[] arrOfProductPrice = new Float[sizeOfList];
		for (int i = 0; i < listOfprice.size(); i++) {
			String price = listOfprice.get(i).getText();
			System.out.println("price is " + price);
			arrOfProductPrice[i] = Float.parseFloat(price);
		}
		Arrays.parallelSort(arrOfProductPrice);
		System.out.println("Highest price of product is  :" + arrOfProductPrice[arrOfProductPrice.length - 1]);
	}

}
