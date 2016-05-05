package com.commonservice.exception;

import com.commonservice.util.LoggerUtil;

public class FileCopyException extends RuntimeException {

	
	LoggerUtil logger=new LoggerUtil(FileCopyException.class);

	private static final long serialVersionUID = 1L;
	
	public FileCopyException(final String msg){
		logMessage(msg);
	}
	private void logMessage(final String msg){
		logger.error(msg);
	}


}
