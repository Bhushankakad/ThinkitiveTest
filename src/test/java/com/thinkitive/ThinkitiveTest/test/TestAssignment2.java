package com.thinkitive.ThinkitiveTest.test;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thinkitive.ThinkitiveTest.base.Base;
import com.thinkitive.ThinkitiveTest.pages.HomePage;
import com.thinkitive.ThinkitiveTest.utilities.Utilities;

public class TestAssignment2 extends Base {
	HomePage homepage = null;
	Utilities utilities = new Utilities();
	 String pathOfProductfile = System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\thinkitive\\ThinkitiveTest\\testdata\\product.txt";

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
		
		utilities.pressEnterKey(driver);

		utilities.click(homepage.selectElementFromDropdown);
		
		driver.navigate().refresh();

		

	}
	
	@Test(dependsOnMethods = { "testWriteProductInfoInTextFile" })
	public void getProductWithHighestPrice() {
		
        // Storing and retriving product info thrugh tree map
		TreeMap<Float,List<String>> infoOfProductHavingHighestValue=utilities.getProductOfHighestPrice(driver);
       
       //Update product.txt with product info
        utilities.writeDataInFile(pathOfProductfile, driver,infoOfProductHavingHighestValue.lastEntry().getValue().get(0),infoOfProductHavingHighestValue.lastKey(),infoOfProductHavingHighestValue.lastEntry().getValue().get(2),infoOfProductHavingHighestValue.lastEntry().getValue().get(1));
		
	}
	
	@AfterClass
	public void closeWindow() {
		driver.quit();
	}
}


