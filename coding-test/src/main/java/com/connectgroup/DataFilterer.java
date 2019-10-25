package com.connectgroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.connectgroup.dataobjects.DataLine;
import com.connectgroup.exceptions.DataFiltererProcessingException;
import com.connectgroup.exceptions.NotEnoughDataException;
import com.connectgroup.utils.DataFilterUtils;

/**
 * DataFilterer class implements the list of methods that can process the
 * application log extracts.
 * 
 * @author Selvameena
 *
 */
public class DataFilterer {

	/**
	 * Filters the list of requests from a specifc country.
	 * 
	 * @param source
	 * @param country
	 * @return
	 */

	public static Collection<DataLine> filterByCountry(Reader source, String country) {
		try {
			// Original List
			List<DataLine> dataLineLists = getDataLineCollections(source);
			// filtered List
			if (dataLineLists != null && dataLineLists.size() > 0) {
				return dataLineLists.stream().filter(dataLineList -> dataLineList.getStrCountry().equals(country))
						.collect(Collectors.toList());
			}
		} catch (DataFiltererProcessingException dfpException) {
			Logger.getLogger("DataFilterer").log(Level.WARNING, dfpException.getMessage());
		}
		return Collections.emptyList();
	}

	/**
	 * Returns the requests whose response time is above a specific limit.
	 * 
	 * @param source
	 * @param country
	 * @param limit
	 * @return
	 */
	public static Collection<DataLine> filterByCountryWithResponseTimeAboveLimit(Reader source, String country,
			long limit) {
		// Original List
		Collection<DataLine> dataLineListsFilteredByCountry = filterByCountry(source, country);

		if (dataLineListsFilteredByCountry != null && dataLineListsFilteredByCountry.size() > 0) {
			return dataLineListsFilteredByCountry.stream()
					.filter(dataLineList -> dataLineList.getResponseTimeInms() > limit).collect(Collectors.toList());
		}

		return Collections.emptyList();
	}

	/**
	 * Returns the requests whose response time is above average.
	 * 
	 * @param source
	 * @return
	 * @throws NotEnoughDataException
	 */
	public static Collection<DataLine> filterByResponseTimeAboveAverage(Reader source) {
		try {
			// Original List
			List<DataLine> dataLineLists = getDataLineCollections(source);
			if (dataLineLists != null && dataLineLists.size() > 0) {
				Double avg = dataLineLists.stream().collect(Collectors.averagingLong(DataLine::getResponseTimeInms));

				return dataLineLists.stream().filter(dataLineList -> dataLineList.getResponseTimeInms() > avg)
						.collect(Collectors.toList());
			}

		} catch (DataFiltererProcessingException dfpException) {
			Logger.getLogger("DataFilterer").log(Level.SEVERE, dfpException.getMessage());
		}
		return Collections.emptyList();
	}

	private static List<DataLine> getDataLineCollections(Reader source) throws DataFiltererProcessingException {
		List<DataLine> dataLineList = new ArrayList<DataLine>();
		try {
			List<String> linesList = readLinesFromFile(source);
			int lineCount = 0;
			for (String strLine : linesList) {
				if (strLine != null && !strLine.contains("REQUEST_TIMESTAMP")) {
					++lineCount;
					String[] dataParts = strLine.split(",");
					if (dataParts.length != 3) {
						//Makes sure the process is carried on even when there is incomplete data, but this info is logged.
						Logger.getLogger("DataFilterer").log(Level.WARNING,
								"Not enough data at line " + lineCount + ". Line reads: " + strLine);
					} else {
						DataLine objDataLine = populateDataLineObject(dataParts);
						dataLineList.add(objDataLine);
					}
				}
			}
		} catch (Exception ex) {
			throw new DataFiltererProcessingException("Exception occurred while processing the log file.");
		}
		return dataLineList;
	}

	private static List<String> readLinesFromFile(Reader source) throws DataFiltererProcessingException {
		List<String> linesList = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(source)) {
			linesList = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			throw new DataFiltererProcessingException("Error while reading the source file - " + e.getMessage());
		}
		return linesList;
	}

	private static DataLine populateDataLineObject(String[] dataParts) {
		DataLine objDataLine = new DataLine();
		//String formattedDate = DataFilterUtils.getFormattedDate(dataParts[0]);
		objDataLine.setRequestTimestamp(dataParts[0]);

		objDataLine.setStrCountry(dataParts[1]);

		objDataLine.setResponseTimeInms(Long.valueOf(dataParts[2]));
		return objDataLine;
	}

	
}