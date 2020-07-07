package com.diffblue.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.diffblue.businessObjects.CodeClass;
import com.diffblue.businessObjects.CodeLine;

public class CodeClassLinesGenerator implements ICodeClassLinesGenerator {

	private final static Logger LOGGER =  
            Logger.getLogger(CodeClassLinesGenerator.class.getName()); 

	public static void main(String[] args) throws Exception {
		String inputFileNameWithPath = "C:\\Users\\sivar\\GitRepository\\CodingAnalyzer\\src\\com\\coding\\firstMaxValues\\FirstMaxValues.java";
		CodeClassLinesGenerator codeClassLinesGenerator = new CodeClassLinesGenerator();
		CodeClass codeClass = codeClassLinesGenerator.generateCodeClassLine(inputFileNameWithPath);
		System.out.println(codeClass.getLinesOfCode());
	}
	
	public CodeClass generateCodeClassLine(String inputFileNameWithPath) throws FileNotFoundException, IOException {
		LOGGER.info("GENERATING FILE");
		CodeClass codeClass = readSourceAndWriteToFile(inputFileNameWithPath);
		return codeClass;
	}

	public CodeClass readSourceAndWriteToFile(String inputFileNameWithPath) {
		FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;
		CodeClass codeClass = null;
		try {
			fileInputStream = new FileInputStream(inputFileNameWithPath);
			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			codeClass = populateCodeClass(bufferedReader);
		} catch (Exception e) {
			LOGGER.severe("Exception occurred while writing the source file: " + e.getMessage());
			LOGGER.fine(e.fillInStackTrace().toString());
		}
		finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			}
			catch (IOException ex) {
				LOGGER.severe(ex.getMessage());
			}
		}
		return codeClass;
	}
	
	private CodeClass populateCodeClass(BufferedReader bufferedReader) {
		CodeClass codeClass = new CodeClass();
		List<CodeLine> codeLines = new ArrayList<CodeLine>();
		String outputFile = "SourceFile.txt";
		FileWriter fileWriter = null;
		File sourceFile = null;
		try {
			sourceFile = new File(outputFile);
			fileWriter = new FileWriter(sourceFile);
			String line;
			int lineNumber = 0;
			while ((line = bufferedReader.readLine()) != null) {
				++lineNumber;
				codeLines.add(populateCodeLine(lineNumber, line));
				fileWriter.write(line);
			}
			codeClass.setFile(sourceFile);
			codeClass.setLinesOfCode(codeLines);
		} catch(IOException ioException) {
			LOGGER.severe(ioException.getMessage());
		} finally {
			try {
				if(fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException ex) {
				System.out.println();
			}
		}
		return codeClass;
	}
	
	private CodeLine populateCodeLine(int lineNumber, String line) {
		CodeLine codeLine = new CodeLine();
		codeLine.setLineNumber(lineNumber);
		codeLine.setContents(line);
		return codeLine;
	}
}
