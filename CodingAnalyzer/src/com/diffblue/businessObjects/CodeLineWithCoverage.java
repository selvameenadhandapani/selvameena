package com.diffblue.businessObjects;

public class CodeLineWithCoverage {
	CodeLine codeLine;
	boolean covered;
	public CodeLine getCodeLine() {
		return codeLine;
	}
	public void setCodeLine(CodeLine codeLine) {
		this.codeLine = codeLine;
	}
	public boolean isCovered() {
		return covered;
	}
	public void setCovered(boolean covered) {
		this.covered = covered;
	}
	
}
