package com.diffblue.performTest;

import java.util.Set;

import com.diffblue.businessObjects.CodeLine;

public class CodeTest implements ICodeTest {

	String testClassName;

	public String getName() {
		return testClassName;
	}
	
	public void setName(String testClassName) {
		this.testClassName = testClassName;
	}
	
	public Set<CodeLine> getCoveredLines() {
		return null;
	}

}
