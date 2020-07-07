package com.coding.firstMaxValues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author Selvameena Dhandapani
 * @Date 8-Jul-2020
 * @Description Accepting an array of integers, this class identifies the first maximum values.
 * The number of maximum values needed varies.
 */
public class FirstMaxValues {
	
	/**
	 * @param inputArray
	 * @param numberOfMaxValues
	 * @return List of First Maximum values.
	 * 
	 */
	public List<Integer> findFirstMaxValues(int[] inputArray, int numberOfMaxValues) {
		List<Integer> inputNumbersList = new ArrayList<Integer>();
		List<Integer> maxValuesList = new ArrayList<Integer>();
		inputNumbersList = convertArrayToList(inputArray);

		for (int maxValueCount = 0; maxValueCount < numberOfMaxValues; maxValueCount++) {
			int maxValueIndex = getMaxValueIndex(inputNumbersList);
			maxValuesList.add(inputNumbersList.get(maxValueIndex));
			inputNumbersList.remove(maxValueIndex);
		}

		return maxValuesList;
	}

	/**
	 * Returns only one integer that's the biggest in the input list. 
	 * @param inputNumbersList
	 * @return
	 */
	public int getMaxValueIndex(List<Integer> inputNumbersList) {
		int maxValue = 0;
		int maxValueIndex = -1;
		for (int i = 0; i < inputNumbersList.size(); i++) {
			if (inputNumbersList.get(i) > maxValue) {
				maxValue = inputNumbersList.get(i);
				maxValueIndex = i;
			}
		}
		return maxValueIndex;
	}

	private List<Integer> convertArrayToList(int[] inputArray) {
		List<Integer> inputNumbersList = new ArrayList<Integer>();
		if (inputArray != null) {
			inputNumbersList = Arrays.stream(inputArray).boxed().collect(Collectors.toList());
		}
		return inputNumbersList;
	}

}