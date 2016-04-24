package com.commonservice;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.commonservice.constants.CommonConstants;

public class FileUtil {
	
	public static List<String> readLines(String fileNameWithLoc) throws IOException{
		
		List<String> lines = FileUtils.readLines(new File(fileNameWithLoc));
		return lines;
		
	}

	

	public static List<File> getTriggerFiles(final String inboxLoc, final String triggerExt)
	{
		String[] extensions = new String[] {triggerExt};
		List<File> files = (List<File>)FileUtils.listFiles(new File(inboxLoc), extensions, true);
		return files; 
	}
	
	/**
	 * 
	 * @param triggerFileNameWithLocAndExt  D:\Naveen\Datasets\SchemaEvolution\20150317T044228_20156423_ORS_ORSEXTR_ISSUE_FULL.ctl
	 * @param triggerExt ctl 
	 * lookupName = 
	 * @return
	 */
	
	public static List<String> getSimilarFiles(final String triggerFileNameWithLocAndExt, final String triggerExt)
	{
		List<String> similarFiles=new ArrayList<String>();
		
		final String lookUpName=getFileName(triggerFileNameWithLocAndExt);
		
		System.out.println("LookUpName:"+lookUpName);
		
		File f=new File(triggerFileNameWithLocAndExt);
		
		File parentFile=f.getParentFile();
		
		File[] files=parentFile.listFiles(new FilenameFilter() {
			
			public boolean accept(File dir, String name) {
				return StringUtils.indexOf(name, lookUpName)!=-1;
			}
		});
		
		for (File file : files) {
			similarFiles.add(file.getAbsolutePath());
		}
		
		return similarFiles;
		
	}
	
	public static Map<String, List<String>> groupSimilarFiles(final String inbox_loc,final String triggerFileExt){
		
		List<File> triggeFiles=getTriggerFiles(inbox_loc, triggerFileExt);
		
		Map<String, List<String>> groupFiles=null;
		
		if(triggeFiles!=null && !triggeFiles.isEmpty())
		{
			groupFiles=new HashMap<String, List<String>>();
			
			for (File file : triggeFiles) {
				final String key=getFileName(file.getAbsolutePath());
				List<String> similarFiles=getSimilarFiles(file.getAbsolutePath(), triggerFileExt);
				groupFiles.put(key, similarFiles);
			}
		}
		return groupFiles;
	}
	
	public static String getFileName(final String fileNameWithExt)
	{
		String onlyFileName=fileNameWithExt.substring(fileNameWithExt.lastIndexOf(CommonConstants.FILE_SEPARATOR.getValue())+1);
		if(onlyFileName==null){
			onlyFileName=fileNameWithExt;
		}
		onlyFileName=onlyFileName.substring(0,onlyFileName.lastIndexOf("."));
		
		return onlyFileName;
	}
	
	
	public boolean moveToArchiveFile(final String inbox_loc,final String archive_loc, final String moveFileLookupName)
	{
		return Boolean.FALSE;
	}
	
	public static String getExtFile(final List<String> groupFiles, final String extFile)
	{
		String ctlFile=null;
		for (String file : groupFiles) {
			if(file.indexOf(("."+extFile))!=-1){
				ctlFile=file;
				break;
			}
		}
		return ctlFile;
	}
	
	public static String getExtFile(final String anyFileNameWithExt, final String extFile)
	{
		final String onlyFileName=getFileName(anyFileNameWithExt);
	
		return onlyFileName.concat(extFile);
	}

	public static void moveFileToLoc(String fileNameWithLoc,String destinationLoc) throws IOException {
		FileUtils.moveFileToDirectory(new File(fileNameWithLoc), new File(destinationLoc), Boolean.TRUE);
		
	}
	
}
