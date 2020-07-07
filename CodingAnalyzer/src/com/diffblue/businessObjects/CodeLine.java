package com.diffblue.businessObjects;

public class CodeLine implements ICodeLine {
	int lineNumber;
	String lineContent;
	
	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getContents() {
		return lineContent;
	}
	
	public void setContents(String lineContent) {
		this.lineContent = lineContent;
	}

}
