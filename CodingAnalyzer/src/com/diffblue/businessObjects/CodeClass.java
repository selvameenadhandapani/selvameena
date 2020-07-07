package com.diffblue.businessObjects;

import java.io.File;
import java.util.List;

public class CodeClass implements ICodeClass {

	File sourceFile;
	List<CodeLine> codeLines;
	
	public List<CodeLine> getLinesOfCode() {
		return codeLines;
	}
	
	public void setLinesOfCode(List<CodeLine> codeLines) {
		this.codeLines = codeLines;
	}
	
	public File getFile() {
		return sourceFile;
	}

	public void setFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}
}
