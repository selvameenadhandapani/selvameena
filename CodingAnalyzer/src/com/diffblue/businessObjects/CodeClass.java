package com.diffblue.businessObjects;

import java.io.File;
import java.util.List;

public class CodeClass implements ICodeClass {

	File sourceFile;
	public List<ICodeLine> getLinesOfCode() {
		return null;
	}

	public File getFile() {
		return sourceFile;
	}

	public void setFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}
}
