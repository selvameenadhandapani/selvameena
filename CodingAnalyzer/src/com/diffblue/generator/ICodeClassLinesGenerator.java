package com.diffblue.generator;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.diffblue.businessObjects.CodeClass;

public interface ICodeClassLinesGenerator {
	public CodeClass generateCodeClassLine(String inputFileName) throws FileNotFoundException, IOException;
}
