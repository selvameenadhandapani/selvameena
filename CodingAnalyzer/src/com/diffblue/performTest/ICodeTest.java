package com.diffblue.performTest;

import java.util.Set;

import com.diffblue.businessObjects.ICodeLine;

/**
 * An interface representing a Java test
 */
public interface ICodeTest {
    /**
     * @return the name of the test
     */
    String getName();

    /**
     * @return the set of line numbers covered by an execution of this test
     */
    Set<ICodeLine> getCoveredLines();
}
