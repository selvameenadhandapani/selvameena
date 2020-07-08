package com.coding.coverage.utils;

import java.util.List;

import com.diffblue.businessObjects.CodeLine;

public class ListUtility {
	public static void addListToList(List<CodeLine> sourceList, List<CodeLine> destinationList) {
		for(CodeLine item : sourceList) {
			destinationList.add(item);
		}
	}
}
