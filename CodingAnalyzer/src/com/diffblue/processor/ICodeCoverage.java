package com.diffblue.processor;

import java.io.File;

public interface ICodeCoverage {
	public File identifyCodeCoverage (String inputFileName) throws Exception;
}
