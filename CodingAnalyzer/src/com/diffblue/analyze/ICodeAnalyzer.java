package com.diffblue.analyze;

import java.util.Set;

import com.diffblue.businessObjects.ICodeLine;
import com.diffblue.performTest.ICodeTest;

/**
 * An interface representing code analysis functions
 */
public interface ICodeAnalyzer {
    /**
     * Runs the given test and returns the covered lines of code
     * @param test to run
     * @return the covered lines of code
     */
    Set<ICodeLine> runTest(ICodeTest test);

    /**
     * Run all tests and returns the covered lines of code
     * @param tests to run
     * @return the covered lines of code
     */
    Set<ICodeLine> runTestSuite(Set<ICodeTest> tests);
}
