package com.diffblue.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import com.diffblue.businessObjects.CodeClass;

public class CodeClassLinesGenerator implements ICodeClassLinesGenerator {

	private final static Logger LOGGER =  
            Logger.getLogger(CodeClassLinesGenerator.class.getName()); 

	public static void main(String[] args) throws Exception {
		String inputFileNameWithPath = "C:\\Users\\sivar\\EclipseWS\\CodingAnalyzer\\src\\com\\coding\\firstMaxValues\\FirstMaxValues.java";
		CodeClassLinesGenerator codeClassLinesGenerator = new CodeClassLinesGenerator();
		codeClassLinesGenerator.generateCodeClassLine(inputFileNameWithPath);
	}
	
	public CodeClass generateCodeClassLine(String inputFileNameWithPath) throws FileNotFoundException, IOException {
		LOGGER.info("GENERATING FILE");
		File sourceFile = readSourceAndWriteToFile(inputFileNameWithPath);
		CodeClass codeClass = new CodeClass();
		codeClass.setFile(sourceFile);
		return null;
	}

	public File readSourceAndWriteToFile(String inputFileNameWithPath) {
		FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;
		File sourceFile = null;
		try {
			fileInputStream = new FileInputStream(inputFileNameWithPath);
			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			sourceFile = writeToOutputFile(bufferedReader);
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
		return sourceFile;
	}
	
	private File writeToOutputFile(BufferedReader bufferedReader) {
		String outputFile = "SourceFile.txt";
		FileWriter fileWriter = null;
		File sourceFile = null;
		try {
			sourceFile = new File(outputFile);
			fileWriter = new FileWriter(sourceFile);
			String s;
			while ((s = bufferedReader.readLine()) != null) {
				//System.out.println(s);
				fileWriter.write(s);
			}
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
		return sourceFile;
	}
}
