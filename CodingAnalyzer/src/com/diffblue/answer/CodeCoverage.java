package com.diffblue.answer;

import java.io.File;

import com.diffblue.generator.CodeClassLinesGenerator;

/**
 * 
 * @author Selvameena Dhandapani
 * @Date 08-Jul-2020
 * 
 * This is the main class that's called first with the file name of the source java class. 
 * 
 */
public class CodeCoverage implements ICodeCoverage {

	/**
	 * Gets the filename of the java code as input and returns with the code coverage details. 
	 */
	public File identifyCodeCoverage(String inputFileNameWithPath) {
		CodeClassLinesGenerator codeClassLinesGenerator = new CodeClassLinesGenerator();
		codeClassLinesGenerator.generateCodeClassLine(inputFileNameWithPath);
		return null;
	}

}
