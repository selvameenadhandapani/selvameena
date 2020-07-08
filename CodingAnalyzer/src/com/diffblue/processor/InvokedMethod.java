package com.diffblue.processor;

import java.util.ArrayList;
import java.util.List;

import com.diffblue.businessObjects.CodeLine;
import com.diffblue.businessObjects.MatchedMethodSignature;

public class InvokedMethod {
	/**
	 * This method returns the list of methods been invoked from the source class.
	 * @param classInstanceInMethod
	 * @param linesInTestMethod
	 * @return
	 */
	public List<MatchedMethodSignature> findInvokedMethodInSourceClass(String classInstanceInMethod, List<CodeLine> linesInTestMethod) {
		List<MatchedMethodSignature> invokedMethodsInTest = new ArrayList<MatchedMethodSignature>();
		for (CodeLine codeLine : linesInTestMethod) {
			if (codeLine.getContents().contains(classInstanceInMethod + ".")) {
				MatchedMethodSignature invokedMethodSignature = getInvokedMethodName(codeLine, classInstanceInMethod);
				invokedMethodsInTest.add(invokedMethodSignature);
			}
		}
		return invokedMethodsInTest;
	}
	
	/**
	 * Currently this method only checks the invoked method name and the number of parameters.
	 * Ideally this should be extended to check the parameter types too.
	 * @param codeLine
	 * @param classInstanceInMethod
	 * @return
	 */
	public MatchedMethodSignature getInvokedMethodName(CodeLine codeLine, String classInstanceInMethod) {
		MatchedMethodSignature matchedMethodSignature = new MatchedMethodSignature();
		String line = codeLine.getContents();
		int classInstanceLength = (classInstanceInMethod + ".").length();
		String invokedMethodName = line.substring(line.indexOf(classInstanceInMethod + ".") + classInstanceLength, line.lastIndexOf("("));
		matchedMethodSignature.setMethodName(invokedMethodName);
		String[] lineBreak = line.split(invokedMethodName);
		String[] params = lineBreak[lineBreak.length - 1].split(",");
		if (params == null) {
			matchedMethodSignature.setNumberOfParameters(0);
		} else {
			matchedMethodSignature.setNumberOfParameters(params.length - 1);
		}
		return matchedMethodSignature;
	}
}
