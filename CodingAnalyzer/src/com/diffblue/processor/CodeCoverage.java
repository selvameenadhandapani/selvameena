package com.diffblue.processor;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.diffblue.businessObjects.CodeClass;
import com.diffblue.businessObjects.TestClassCode;
import com.diffblue.generator.CodeClassLinesGenerator;
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

	public static void main(String args[]) throws Exception {
		CodeCoverage codeCoverage = new CodeCoverage();
		codeCoverage.identifyCodeCoverage("inputFileNameWithPath");
	}

	/**
	 * Gets the filename of the java code as input and returns with the code coverage details. 
	 */
	public File identifyCodeCoverage(String inputFileNameWithPath) throws Exception {
		CodeClassLinesGenerator codeClassLinesGenerator = new CodeClassLinesGenerator();
		CodeClass codeClass = codeClassLinesGenerator.generateCodeClassLine(inputFileNameWithPath);
		
		String testClassname = "com.coding.firstMaxValues.test.FirstMaxValuesTest";
		CodeTest codeTest = new CodeTest();
		codeTest.setName(testClassname);
		
		List<Method> listOfTestMethods = identifyTestMethods(codeTest);
		List<Method> listOfAllMethods = identifyMethods(codeTest);
		//listOfTestMethods.forEach(method -> {System.out.println(method.getName());});
		System.out.println(Class.forName(codeTest.getName()).getMethod("testFirstTwoMaxValues", null));
		TestClassCode testClassCode = readTestClassFile(testFileNameWithPath);
		MatchMethods matchMethods = new MatchMethods();
		matchMethods.matchTestMethods(codeClass, testClassCode, listOfTestMethods, listOfAllMethods);
		return null;
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

	public List<Method> identifyTestMethods(CodeTest codeTest) {
		Method[] methods = null;
		try {
			methods = Class.forName(codeTest.getName()).getMethods();
			List<Method> listMethods = Arrays.asList(methods);

			return listMethods.stream().filter(method -> (method.getName().startsWith("test")))
					.collect(Collectors.toList());
		} catch (ClassNotFoundException cnfe) {
			LOGGER.severe("Exception occurred while reading the methods from test file: " + cnfe.getMessage());
		}
		return null;
	}
	
	public List<Method> identifyMethods(CodeTest codeTest) {
		Method[] methods = null;
		try {
			methods = Class.forName(codeTest.getName()).getMethods();
			return Arrays.asList(methods);
		} catch (ClassNotFoundException cnfe) {
			LOGGER.severe("Exception occurred while reading the methods from test file: " + cnfe.getMessage());
		}
		return null;
	}

}
