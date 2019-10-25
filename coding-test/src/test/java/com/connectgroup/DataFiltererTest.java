package com.connectgroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

import com.connectgroup.dataobjects.DataLine;
import com.connectgroup.utils.DataFilterUtils;

import static org.junit.Assert.*;


/**
 * Test class to test the functionalities of DataFiltere class. 
 * @author Selvameena
 *
 */
public class DataFiltererTest {
	/**
	 * Asserts if the response is an empty collection when an empty file is sent as input.
	 * @throws FileNotFoundException
	 */
	@Test
	public void shouldReturnEmptyCollection_WhenLogFileIsEmpty()
			throws FileNotFoundException {
		assertTrue(DataFilterer.filterByCountry(
				openFile("src/test/resources/empty"), "GB").isEmpty());
	}

	/**
	 * Asserts the response contains the filtered data for a requested country name. 
	 * @throws FileNotFoundException
	 */
	
	@Test
	public void filterByCountry() throws FileNotFoundException {
		Collection<DataLine> dataLines = DataFilterer.filterByCountry(
				openFile("src/test/resources/multi-lines"), "GB");
		if (dataLines != null) {
			Iterator<DataLine> dlIterator = dataLines.iterator();
			while (dlIterator.hasNext()) {
				DataLine objDataLine = (DataLine) dlIterator.next();
				
				assertEquals("Timestamp doesn't match", Long.valueOf(1432917066), Long.valueOf(objDataLine.getRequestTimestamp()));
				assertEquals("Response time doesn't match", Long.valueOf(37), Long.valueOf(objDataLine.getResponseTimeInms()));
				assertEquals("Country doesn't match", "GB", Long.valueOf(objDataLine.getStrCountry()));
			}
		}
	}

	/**
	 * Asserts the returned list of data that contains the requests from a specific country, that took more than a specified response time limit.
	 * @throws FileNotFoundException
	 */
	@Test
	public void filterByCountryRespTimeMoreThan() throws FileNotFoundException {
		Collection<DataLine> dataLines = DataFilterer
				.filterByCountryWithResponseTimeAboveLimit(
						openFile("src/test/resources/multi-lines"), "US", 800L);
		if (dataLines != null) {
			Iterator<DataLine> dlIterator = dataLines.iterator();
			while (dlIterator.hasNext()) {
				DataLine objDataLine = (DataLine) dlIterator.next();
				assertEquals("Timestamp doesn't match", Long.valueOf(1432484176), Long.valueOf(objDataLine.getRequestTimestamp()));
				assertEquals("Response time doesn't match", Long.valueOf(850), Long.valueOf(objDataLine.getResponseTimeInms()));
				assertEquals("Country doesn't match", "US", Long.valueOf(objDataLine.getStrCountry()));
			
			}
		}
	}
	
	/**
	 * 
	 * Asserts the returned list of data that contains the requests who response took more than average. 
	 * @throws FileNotFoundException
	 */
	
	@Test
	public void filterByAboveAverage() throws FileNotFoundException {
		Collection<DataLine> dataLines = DataFilterer
				.filterByResponseTimeAboveAverage(openFile("src/test/resources/multi-lines"));
		DataLine[] expectedList = buildExpectedListForAverageFilter();
		
		DataLine[] actualList = dataLines.toArray(new DataLine[3]);
		assertEquals(expectedList.length, actualList.length);
		assertEquals(expectedList[0].getResponseTimeInms(), actualList[0].getResponseTimeInms());
		assertEquals(expectedList[2].getStrCountry(), actualList[0].getStrCountry());
		//Just tested few elements to confirm the result
	}
	

	private DataLine[] buildExpectedListForAverageFilter() {
		DataLine dataLine1 = new DataLine();
		dataLine1.setRequestTimestamp("1433190845");
		dataLine1.setResponseTimeInms(539);
		dataLine1.setStrCountry("US");
		
		DataLine dataLine2 = new DataLine();
		dataLine2.setRequestTimestamp("1433666287");
		dataLine2.setResponseTimeInms(789);
		dataLine2.setStrCountry("US");
		
		DataLine dataLine3 = new DataLine();
		dataLine3.setRequestTimestamp("1432484176");
		dataLine3.setResponseTimeInms(850);
		dataLine3.setStrCountry("US");
		
		DataLine[] dataLines = {dataLine1, dataLine2, dataLine3};
		return dataLines;
	}
	
	/**
	 * Asserts that only one row is returned for DE, as the 2nd row is incomplete. 
	 * @throws FileNotFoundException
	 */
	
	@Test
	public void testIncompleteData() throws FileNotFoundException {
		Collection<DataLine> dataLines = DataFilterer.filterByCountry(
				openFile("src/test/resources/incomplete-data"), "DE");
		if (dataLines != null) {
			Iterator<DataLine> dlIterator = dataLines.iterator();
			DataLine[] actualList = dataLines.toArray(new DataLine[1]);
			assertEquals(1, actualList.length);
		}
	}

	private FileReader openFile(String filename) throws FileNotFoundException {
		return new FileReader(new File(filename));
	}
}
