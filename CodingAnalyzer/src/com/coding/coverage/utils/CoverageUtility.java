package com.coding.coverage.utils;

import java.io.File;
import java.util.List;

import com.diffblue.businessObjects.CodeLine;

public class CoverageUtility {
	public static boolean isSingleLineComment(CodeLine codeLine) {
		if(codeLine != null && codeLine.getContents().startsWith("\\") && codeLine.getContents().startsWith("\\*")) {
			return true;
		}
		return false;
	}
	
	public static boolean isMultiLineComment(CodeLine codeLine, List<CodeLine> codeLines) {
		int lineNumberOfCodeLine = 0;
		if(codeLines != null) {
			//CodeLine foundLine = codeLines.stream().findFirst(codeLineInList -> (codeLineInList.indexOf(codeLine.getContents()) >= 0));
			//Get the line number. 
			
			//Then go through the list starting from the foundlinenumber, and check if */ comes before /*. If so its part of a multiline comment. Else its not a comment. 
			
		}
		return false;
	}
	
	public static boolean isMultiLineComment(int lineIndex, List<CodeLine> codeLines) {
		for (int index = lineIndex ; index > 0 ; index--) {
			if (codeLines.get(index) != null && codeLines.get(index).getContents().contains("\\*")) {
				/*
				 * More logic needed here to find out there is no ending of the block comment, even if its there, it comes before the start 
				 * of the block comment. This means the input codeLine is definitely part of a block comment */
				 
				 return true;
			}
		}
		return false;
	}
	
	public static boolean isComment(CodeLine codeLine, int lineIndex, List<CodeLine> codeLines) {
		if(!isSingleLineComment(codeLine) && !isMultiLineComment(lineIndex, codeLines)) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void main(String args[]) {
		String publicClassName = findPublicClassName("C:\\Users\\sivar\\GitRepository\\CodingAnalyzer\\src\\com\\coding\\firstMaxValues\\FirstMaxValues.java");
		System.out.println(publicClassName);
	}
	
	public static String findPublicClassName(String fileNameWithPath) {
		String publicClassName = null;
		if (fileNameWithPath != null) {
			int indexDotJava = fileNameWithPath.indexOf(".java");
			if (indexDotJava > 0) {
				int lastSeparatorIndex = fileNameWithPath.lastIndexOf(File.separator);
				publicClassName = fileNameWithPath.substring(lastSeparatorIndex + 1, indexDotJava);
			}
		}
		return publicClassName;
	}
}
