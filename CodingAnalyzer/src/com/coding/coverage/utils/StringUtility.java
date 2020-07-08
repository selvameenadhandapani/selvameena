package com.coding.coverage.utils;

public class StringUtility {
	public static String trim(String inputString) {
		inputString = emptyIfNull(inputString);
		inputString = inputString.trim();
		return inputString;
	}
	
	public static String emptyIfNull(String inputString) {
		if (inputString == null) {
			return "";
		} else {
			return inputString;
		}
	}
	
	public static String removeIndentation(String inputString) {
		if (inputString != null) {
			inputString = inputString.replaceAll("\t", "");
		}
		return inputString;
	}
}

