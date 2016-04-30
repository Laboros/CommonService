package com.commonservice.exception;

import com.commonservice.util.LoggerUtil;

public class InvalidArgException extends Exception {

	
	LoggerUtil logger=new LoggerUtil(InvalidArgException.class);

	private static final long serialVersionUID = 1L;
	
	public InvalidArgException(final String msg){
		logMessage(msg);
	}
	private void logMessage(final String msg){
		logger.error(msg);
	}


}
