package com.coding.firstMaxValues.test;

import java.util.List;

import com.coding.firstMaxValues.FirstMaxValues;

public class FirstMaxValuesTest {

	public void testFirstTwoMaxValues() {
		FirstMaxValues firstTwoMaxValues = new FirstMaxValues();
		int[] inputArray = {2, 5, 7, 21, 54, 12, 36};
		int numberOfMaxValuesNeeded = 2;
		
		List<Integer> maxValues = firstTwoMaxValues.findFirstMaxValues(inputArray, numberOfMaxValuesNeeded);
		
		System.out.println(maxValues.toString());
	}
	
	public void testFirstFourMaxValues() {
		FirstMaxValues firstTwoMaxValues = new FirstMaxValues();
		int[] inputArray = {2, 5, 7, 21, 54, 12, 36};
		int numberOfMaxValuesNeeded = 4;
		
		List<Integer> maxValues = firstTwoMaxValues.findFirstMaxValues(inputArray, numberOfMaxValuesNeeded);
		
		System.out.println(maxValues.toString());
	}
	
	public void testFirstOneMaxValue() {
		FirstMaxValues firstTwoMaxValues = new FirstMaxValues();
		int[] inputArray = {2, 5, 7, 21, 54, 12, 36};
		int numberOfMaxValuesNeeded = 1;
		
		List<Integer> maxValues = firstTwoMaxValues.findFirstMaxValues(inputArray, numberOfMaxValuesNeeded);
		
		System.out.println(maxValues.toString());
	}
	
	

}
