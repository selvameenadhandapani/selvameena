package com.diffblue.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.diffblue.businessObjects.CodeClass;
import com.diffblue.businessObjects.CodeLine;
import com.diffblue.businessObjects.CodeLineWithCoverage;

public class CoverageReport {
	private final static Logger LOGGER =  
            Logger.getLogger(CoverageReport.class.getName()); 

	public File writeSourceCodeAndCoveragePrefixToFile(List<CodeLine> finalListOfCoveredLines, CodeClass codeClass) {
		List<CodeLineWithCoverage> codeLinesWithCoverageInfo = new ArrayList<CodeLineWithCoverage>();
		for (CodeLine codeLine : codeClass.getLinesOfCode()) {
			int lineNumber = codeLine.getLineNumber();

			long foundLineCoverage = finalListOfCoveredLines.stream()
					.filter(coveredLine -> lineNumber == coveredLine.getLineNumber()).count();

			populateCoverageInfo(foundLineCoverage, codeLine, codeLinesWithCoverageInfo);
			printCoverageInfoToFile(codeLinesWithCoverageInfo);
		}
		return new File("");
	}

	private void populateCoverageInfo(long foundLineCoverage, CodeLine codeLine,
			List<CodeLineWithCoverage> codeLinesWithCoverageInfo) {
		CodeLineWithCoverage codeLineWithCoverage = new CodeLineWithCoverage();
		codeLineWithCoverage.setCodeLine(codeLine);
		if (foundLineCoverage > 0) {
			codeLineWithCoverage.setCovered(true);
		} else {
			codeLineWithCoverage.setCovered(false);
		}
		codeLinesWithCoverageInfo.add(codeLineWithCoverage);
	}

	public void printCoverageInfoToFile(List<CodeLineWithCoverage> codeLinesWithCoverageInfo) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter("CoverageOutputReport.txt", false)));

			for (CodeLineWithCoverage codeLineWithCoverage : codeLinesWithCoverageInfo) {
				pw.println(codeLineWithCoverage.isCovered() + "\t" + codeLineWithCoverage.getCodeLine().getLineNumber()
						+ "\t" + codeLineWithCoverage.getCodeLine().getContents());
			}
			
		} catch (IOException ioException) {
			LOGGER.severe("Exception occurred while writing the coverage output report to file: " + ioException.getMessage());
			LOGGER.fine(ioException.fillInStackTrace().toString());
		} finally {
			if(pw != null) {
				pw.close();
			}
		}
	}
}
