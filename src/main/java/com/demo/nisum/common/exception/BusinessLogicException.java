package com.demo.nisum.common.exception;

public class BusinessLogicException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3040759314470723382L;

	public BusinessLogicException(String detailMessage) {
		super(detailMessage);		
	}
	
	public BusinessLogicException(String errorMessage, Throwable error) {
		super(errorMessage, error);
	}
}
