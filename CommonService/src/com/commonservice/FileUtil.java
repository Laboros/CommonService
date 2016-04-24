package com.commonservice;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtil {
	/**
	 * 
	 * @param fileNameWithLocation
	 * @return the complete file with format
	 * @throws IOException
	 */
	public static String readFileWithFormat(final String fileNameWithLocation) throws IOException {
		String fileContent = null;
		File inputFile = new File(fileNameWithLocation);
		if (inputFile.exists()) {
			fileContent = FileUtils.readFileToString(inputFile);
		} else {
			// TODO throw exception
		}
		return fileContent;
	}
	
	/**
	 * 
	 * @param fileNameWithLocation
	 * @param destinationLoc
	 * @return true/false indicating the file was moved successfully
	 * Please use HDFSService for moving Files from Local file to HDFS files
	 */
	public static boolean moveFileToDestination(final String fileNameWithLocation,final String destinationLoc)
	{
		
		return Boolean.FALSE;
	}
}
