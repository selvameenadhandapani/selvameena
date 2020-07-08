package com.diffblue.processor;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.diffblue.businessObjects.CodeClass;
import com.diffblue.businessObjects.CodeLine;
import com.diffblue.businessObjects.TestClassCode;
import com.diffblue.generator.CodeClassLinesGenerator;
import com.diffblue.generator.CoverageReport;
import com.diffblue.performTest.CodeTest;

/**
 * 
 * @author Selvameena Dhandapani
 * @Date 08-Jul-2020
 * 
 *       This is the main class that's called first with the file name of the
 *       source java class.
 * 
 */
public class CodeCoverage implements ICodeCoverage {

	private final static Logger LOGGER = Logger.getLogger(CodeCoverage.class.getName());
	String testFileNameWithPath = "C:\\Users\\sivar\\GitRepository\\CodingAnalyzer\\src\\com\\coding\\firstMaxValues\\test\\FirstMaxValuesTest.java";
	String sourceFileNameWithPath = "C:\\Users\\sivar\\GitRepository\\CodingAnalyzer\\src\\com\\coding\\firstMaxValues\\FirstMaxValues.java";

	public static void main(String args[]) throws Exception {
		CodeCoverage codeCoverage = new CodeCoverage();
		codeCoverage.identifyCodeCoverage();
	}

	/**
	 * Gets the filename of the java code as input and returns a file with the code coverage details. 
	 */
	public void identifyCodeCoverage() throws Exception {
		CodeClass codeClass = readFileAndPopulateCodeClass(sourceFileNameWithPath);
		
		String testClassname = "com.coding.firstMaxValues.test.FirstMaxValuesTest";
		CodeTest codeTest = new CodeTest();
		codeTest.setName(testClassname);
		
		List<Method> listOfTestMethods = identifyTestMethods(codeTest);
		List<Method> listOfAllMethods = identifyMethods(codeTest);
		TestClassCode testClassCode = readTestClassFile(testFileNameWithPath);
		if (codeClass != null) {
			MatchMethods matchMethods = new MatchMethods();

			List<CodeLine> finalListOfCoveredLines = matchMethods.matchTestMethods(codeClass, testClassCode, listOfTestMethods, listOfAllMethods);
			new CoverageReport().writeSourceCodeAndCoveragePrefixToFile(finalListOfCoveredLines, codeClass);
		}
	}

	/**
	 * This method identifies the test methods that are to be executed.
	 * @param codeTest
	 * @return
	 */
	public List<Method> identifyTestMethods(CodeTest codeTest) {
		Method[] methods = null;
		try {
			methods = Class.forName(codeTest.getName()).getMethods();
			if(methods != null) {
				List<Method> listMethods = Arrays.asList(methods);
				if(listMethods != null) {
					return listMethods.stream().filter(method -> (method.getName().startsWith("test")))
							.collect(Collectors.toList());
				}
			}
		} catch (ClassNotFoundException cnfe) {
			LOGGER.severe("Exception occurred while reading the methods from test file: " + cnfe.getMessage());
		}
		return null;
	}
	
	/**
	 * This method gets list of all methods. This is just for marker purpose to identify the beginning and end of the method.
	 * @param codeTest
	 * @return
	 */
	public List<Method> identifyMethods(CodeTest codeTest) {
		Method[] methods = null;
		try {
			methods = Class.forName(codeTest.getName()).getDeclaredMethods();
			if (methods != null) {
				return Arrays.asList(methods);
			}
		} catch (ClassNotFoundException cnfe) {
			LOGGER.severe("Exception occurred while reading the methods from test file: " + cnfe.getMessage());
		}
		return null;
	}
	
	private CodeClass readFileAndPopulateCodeClass(String inputFileNameWithPath) {
		CodeClass codeClass = null;
		try {	
			CodeClassLinesGenerator codeClassLinesGenerator = new CodeClassLinesGenerator();
			codeClass = codeClassLinesGenerator.generateCodeClassLine(inputFileNameWithPath);
		} catch (Exception exception) {
			LOGGER.severe("Exception occurred while generating CodeClass from reading the source file: " + exception.getMessage());
			LOGGER.fine(exception.fillInStackTrace().toString());
		}
		return codeClass;
	}
	
	private TestClassCode readTestClassFile(String testFileNameWithPath) throws IOException {
		CodeClassLinesGenerator codeClassLinesGenerator = new CodeClassLinesGenerator();
		CodeClass codeClass = codeClassLinesGenerator.generateCodeClassLine(testFileNameWithPath);
		TestClassCode testClassCode = getTestFileFromGeneratedOutput(codeClass);
		return testClassCode;
	}

	private TestClassCode getTestFileFromGeneratedOutput(CodeClass codeClass) {
		TestClassCode testClassCode = new TestClassCode();
		testClassCode.setFile(codeClass.getFile());
		testClassCode.setLinesOfCode(codeClass.getLinesOfCode());
		testClassCode.setTestClassName(codeClass.getSourceClassName());
		return testClassCode;
	}

}
