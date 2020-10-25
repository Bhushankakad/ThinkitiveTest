package com.thinkitive.ThinkitiveTest.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TestAssignment1 {

	public static void main(String[] args) {
		String pathOfTestDataFile = System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\thinkitive\\ThinkitiveTest\\testdata\\input.txt";
		int numberTomultiply = 5;
		int[] arrOfnumberAfterGetsMultiplies = null;
		try {
			
			//Read the data form input file
			String line;
			FileInputStream fis = new FileInputStream(pathOfTestDataFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));

			String pathOfTestResultFile = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\thinkitive\\ThinkitiveTest\\testdata\\result.txt";
			FileOutputStream fos = new FileOutputStream(pathOfTestResultFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			//process the data
			while ((line = br.readLine()) != null) {
				String[] arrayOfNum = line.split("\\|");
				arrOfnumberAfterGetsMultiplies = new int[arrayOfNum.length];
				for (int i = 0; i < arrayOfNum.length; i++) {
					int valueOfnumber = Integer.parseInt(arrayOfNum[i]);
					
					//Step : multiply number with value
					
					int valueOfFinal = valueOfnumber * numberTomultiply;
					arrOfnumberAfterGetsMultiplies[i] = valueOfFinal;
					
					//write data into result.txt file
					bw.write(arrOfnumberAfterGetsMultiplies[i] + "|");
					System.out.print(arrOfnumberAfterGetsMultiplies[i] + "|");
					bw.flush();

				}

				System.out.println();
				bw.newLine();

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
		} finally {

		}

	}

}
