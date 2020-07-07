package com.diffblue.businessObjects;

import java.io.File;

/**
 * An interface representing a line of Java code in a file
 */
public interface ICodeLine {
    /**
     * @return the line number of the code under test
     */
    int getLineNumber();

    /**
     * @return the string representation of the this line of code
     */
    String getContents();
}
