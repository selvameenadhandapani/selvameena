package com.coding.coverage.utils;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.diffblue.businessObjects.CodeLine;

public class CoverageUtilityTest {

	@Test
	public void testFindPublicClassName() {
		String sourceFileNameWithPath = "C:\\Users\\sivar\\GitRepository\\CodingAnalyzer\\src\\com\\coding\\firstMaxValues\\FirstMaxValues.java";
		String fileName = CoverageUtility.findPublicClassName(sourceFileNameWithPath);
		assertEquals("FirstMaxValues", fileName);
	}
	
	@Test
	public void testIsSingleLineComment() {
		CodeLine codeLine = new CodeLine();
		codeLine.setLineNumber(100);
		codeLine.setContents("//Hello");
		assertEquals(CoverageUtility.isSingleLineComment(codeLine), true);
	}
	
	@Test
	public void testIsMultiLineComment() {
		List<CodeLine> codeLines = new ArrayList<CodeLine>();
		CodeLine codeLine1 = new CodeLine();
		codeLine1.setLineNumber(1);
		codeLine1.setContents("/*Hello");
		codeLines.add(codeLine1);
		
		CodeLine codeLine2 = new CodeLine();
		codeLine2.setLineNumber(2);
		codeLine2.setContents("Hi ");
		codeLines.add(codeLine2);
		
		CodeLine codeLine3 = new CodeLine();
		codeLine3.setLineNumber(3);
		codeLine3.setContents("How are you");
		codeLines.add(codeLine3);
		
		CodeLine codeLine4 = new CodeLine();
		codeLine4.setLineNumber(4);
		codeLine4.setContents("Good bye");
		codeLines.add(codeLine4);
		
		assertEquals(CoverageUtility.isMultiLineComment(2, codeLines), true);
	}
}
