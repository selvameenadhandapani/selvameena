package com.diffblue.answer;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.diffblue.generator.CodeClassLinesGenerator;
import com.diffblue.performTest.CodeTest;

/**
 * 
 * @author Selvameena Dhandapani
 * @Date 08-Jul-2020
 * 
 * This is the main class that's called first with the file name of the source java class. 
 * 
 */
public class CodeCoverage implements ICodeCoverage {

	private final static Logger LOGGER =  
            Logger.getLogger(CodeCoverage.class.getName()); 
	
	public static void main(String args[]) throws Exception {
		CodeCoverage codeCoverage = new CodeCoverage();
		codeCoverage.identifyCodeCoverage("inputFileNameWithPath");
	}
	/**
	 * Gets the filename of the java code as input and returns with the code coverage details. 
	 */
	public File identifyCodeCoverage(String inputFileNameWithPath) throws Exception {
		//DSMTODO CodeClassLinesGenerator codeClassLinesGenerator = new CodeClassLinesGenerator();
		//codeClassLinesGenerator.generateCodeClassLine(inputFileNameWithPath);
		
		String testClassname = "FirstMaxValuesTest";
		CodeTest codeTest = new CodeTest();
		codeTest.setName(testClassname);
		
		identifyTestMethods(codeTest);
		return null;
	}
	
	public List<String> identifyTestMethods(CodeTest codeTest) {
		Method[] methods = null;
		try {
			methods = Class.forName(codeTest.getName()).getMethods();
			Arrays.asList(methods).forEach(method->{System.out.println(method.getName());});
		}
		catch( ClassNotFoundException cnfe) {
			LOGGER.severe("Exception occurred while reading the methods from test file: " + cnfe.getMessage());
		}
		return null;
	}

}
