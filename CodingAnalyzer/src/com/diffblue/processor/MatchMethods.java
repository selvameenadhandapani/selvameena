package com.diffblue.processor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.coding.coverage.utils.CoverageUtility;
import com.coding.coverage.utils.ListUtility;
import com.coding.coverage.utils.MethodUtility;
import com.coding.coverage.utils.StringUtility;
import com.diffblue.businessObjects.CodeClass;
import com.diffblue.businessObjects.CodeLine;
import com.diffblue.businessObjects.MatchedMethodSignature;
import com.diffblue.businessObjects.TestClassCode;

public class MatchMethods {
	private final static Logger LOGGER = Logger.getLogger(MatchMethods.class.getName());

	public List<CodeLine> matchTestMethods(CodeClass codeClass, TestClassCode testClassCode, List<Method> listOfTestMethods, List<Method> listOfAllMethods) {
		List<CodeLine> finalListOfCoveredLines = new ArrayList<CodeLine>();
		if (listOfTestMethods != null && listOfTestMethods.size() > 0) {
			listOfTestMethods.forEach(testMethod -> {
				List<CodeLine> linesInTestMethod = getLinesOfCodeInMethod(testMethod.getName(), testClassCode.getLinesOfCode(), listOfAllMethods);
				String classInstanceInMethod = findClassInstanceInMethod(codeClass, linesInTestMethod);
				InvokedMethod invokedMethod = new InvokedMethod();
				List<MatchedMethodSignature> invokedMethodsInTest = invokedMethod.findInvokedMethodInSourceClass(classInstanceInMethod, linesInTestMethod);
				List<CodeLine> coveredLines = getCoveredLines(invokedMethodsInTest, codeClass);
				ListUtility.addListToList(coveredLines, finalListOfCoveredLines);
			});
		}
		return finalListOfCoveredLines;
	}

	public List<CodeLine> getLinesOfCodeInMethod(String methodName, List<CodeLine> linesOfCode,
			List<Method> listOfAllMethods) {
		int lineIndex = 0;
		int endOfMethodLineIndex = 0;

		for (CodeLine lineOfCode : linesOfCode) {
			++lineIndex;
			if (lineOfCode.getContents().indexOf(methodName) >= 0) {
				if (!CoverageUtility.isComment(lineOfCode, lineIndex, linesOfCode)) {
					endOfMethodLineIndex = identifyEndOfMethod(lineIndex, linesOfCode, listOfAllMethods);
					break;
				} else {
					continue;
				}
			}
			
		}

		List<CodeLine> linesInTestMethod = getLinesFromBeginningToEnd(lineIndex, endOfMethodLineIndex, linesOfCode);
		return linesInTestMethod;
	}

	public int identifyEndOfMethod(int beginningLineIndex, List<CodeLine> testClassLineOfCode,
			List<Method> listOfAllMethods) {
		List<String> listOfMethodFullNames = MethodUtility.GetMethodFullValue(listOfAllMethods);
		int endOfMethodLineIndex = 0;
		for (int lineIndex = beginningLineIndex; lineIndex < testClassLineOfCode.size(); lineIndex++) {
			String lineContent = testClassLineOfCode.get(lineIndex).getContents();
			if (testClassLineOfCode.get(lineIndex) != null
					&& lineContent.length() > 1) {
				if(MethodUtility.isStartOfNewMethod(listOfMethodFullNames, lineContent)) {
					endOfMethodLineIndex = lineIndex;
					break;
				}
			}
		}
		return endOfMethodLineIndex;
	}

	public List<CodeLine> getLinesFromBeginningToEnd(int beginningLineIndex, int endOfMethodLineIndex,
			List<CodeLine> testClassLineOfCode) {
		List<CodeLine> linesInTestMethod = new ArrayList<CodeLine>();
		for (int lineIndex = beginningLineIndex; lineIndex <= endOfMethodLineIndex; lineIndex++) {
			linesInTestMethod.add(testClassLineOfCode.get(lineIndex));
		}
		return linesInTestMethod;
	}

	public String findClassInstanceInMethod(CodeClass codeClass, List<CodeLine> linesInTestMethod) {
		String classInstanceName = "";
		if (codeClass != null && codeClass.getSourceClassName() != null) {
			for (CodeLine codeLine : linesInTestMethod) {
				String line = codeLine.getContents();
				if (line.indexOf(codeClass.getSourceClassName()) > 0 && line.indexOf("new") > 0
						&& line.indexOf(codeClass.getSourceClassName() + "()") > 0) {
					classInstanceName = line.substring(line.indexOf(codeClass.getSourceClassName()), line.indexOf("="));
					classInstanceName = StringUtility.trim(classInstanceName);
				}
			}
		}
		return classInstanceName;
	}

	public List<CodeLine> getCoveredLines(List<MatchedMethodSignature> invokedMethodsInTest, CodeClass codeClass) {
		List<CodeLine> coveredLines = new ArrayList<CodeLine>();

		try {
			List<Method> listOfAllMethods = Arrays.asList(Class.forName(codeClass.getSourceClassName()).getMethods());
			for (MatchedMethodSignature invokedMethod : invokedMethodsInTest) {
				List<CodeLine> coveredCodeLines = getLinesOfCodeInMethod(invokedMethod.getMethodName(),
						codeClass.getLinesOfCode(), listOfAllMethods);
				ListUtility.addListToList(coveredLines, coveredCodeLines);
			}
		} catch (ClassNotFoundException cnfe) {
			LOGGER.severe("Exception occurred while identifying code coverage: " + cnfe.getMessage());
		}
		return coveredLines;
	}

}
