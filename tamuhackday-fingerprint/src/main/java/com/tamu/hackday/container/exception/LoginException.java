package com.tamu.hackday.container.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(LoginException.class);
	ExceptionCodes errorCode;
	String message;
	
	public LoginException(ExceptionCodes errorCode) {
		this.errorCode = errorCode;
	}

	public LoginException(String message, ExceptionCodes errorCode) {
		super(message);
		this.errorCode = errorCode;
		this.message = message;
	}

	public LoginException(String message, Throwable cause, ExceptionCodes errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode.getStatusCode();

	}

	public ExceptionCodes getExceptionCode() {
		return errorCode;

	}

	public void setErrorCode(ExceptionCodes errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return this.message;

	}

	public String toJson() {
		return "{\"error_code\":\"" + this.getErrorCode() + "\",\"error_message\":\"" + this.getMessage() + "\"}";
	}

	public void logException() {
		log.error(this.toJson());
	}
}
