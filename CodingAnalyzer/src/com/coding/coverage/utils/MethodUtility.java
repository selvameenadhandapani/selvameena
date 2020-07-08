package com.coding.coverage.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodUtility {
	public static boolean isStartOfNewMethod(List<String> listOfAllMethods, String lineContent) {
		boolean isStartOfNewMethod = false; 
		lineContent = StringUtility.trim(lineContent);
		if (lineContent.length() == 0 || !isPossibleMethod(lineContent) || lineContent.contains("=")) {
			return false;
		} else {
			String methodSignatureWords = lineContent.substring(0, lineContent.indexOf("("));
			String[] wordsToCheck = methodSignatureWords.split(" "); 
			
				if(listOfAllMethods !=null && listOfAllMethods.size() > 0 ) {
					for (String method : listOfAllMethods) {
						String methodName = method;
						boolean methodFound = false;
						for (int wordIndex = 0 ; wordIndex < wordsToCheck.length ; wordIndex++) {
							if (methodName.contains(wordsToCheck[wordIndex])) {
								methodFound = true;
								continue;
							} else {
								methodFound = false;
								break;
							}
						}
						if(!methodFound) {
							continue;
						} else {
							isStartOfNewMethod = true;
							break;
						}
					}
				}
			}
	
		return isStartOfNewMethod;
	}
	
	public static boolean isPossibleMethod(String lineContent) {
		boolean possibleMethod = false; 
		if(lineContent.indexOf("(") > 0 && lineContent.indexOf(")") > 0) {
			possibleMethod = true;
		} 
		
		if(StringUtility.trim(StringUtility.removeIndentation(lineContent)).length() == 0) {
			possibleMethod = false;
		}
		return possibleMethod;
	}
	public static List<String> GetMethodFullValue(List<Method> listOfMethods) {
		List<String> listOfMethodNames = new ArrayList<String>();
		listOfMethods.forEach(method -> {
			listOfMethodNames.add(method.toString());
		});
		return listOfMethodNames;
	}
}
