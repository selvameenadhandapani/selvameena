package com.coding.coverage.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
 

public class MethodUtilityTest {

	@Test
	public void testIsStartOfNewMethod () {
		List<String> listOfAllMethods = new ArrayList<String>();
		listOfAllMethods.add("public void com.coding.firstMaxValues.test.FirstMaxValuesTest.testFirstTwoMaxValues()");
		listOfAllMethods.add("public void com.coding.firstMaxValues.test.FirstMaxValuesTest.testFirstOneMaxValue()");
		listOfAllMethods.add("public void com.coding.firstMaxValues.test.FirstMaxValuesTest.testFirstFourMaxValues()");
		
		String lineContent = "public void testFirstTwoMaxValues() {";
		
		assertEquals(true, MethodUtility.isStartOfNewMethod(listOfAllMethods, lineContent));
	}

}
