package com.diffblue.businessObjects;

import java.io.File;
import java.util.List;

public class TestClassCode implements ICodeClass {
	File testClassFile;
	List<CodeLine> codeLines;
	String testClassName; 
	
	public String getTestClassName() {
		return testClassName;
	}

	public void setTestClassName(String testClassName) {
		this.testClassName = testClassName;
	}

	public List<CodeLine> getLinesOfCode() {
		return codeLines;
	}
	
	public void setLinesOfCode(List<CodeLine> codeLines) {
		this.codeLines = codeLines;
	}
	
	public File getFile() {
		return testClassFile;
	}

	public void setFile(File testClassFile) {
		this.testClassFile = testClassFile;
	}
}
