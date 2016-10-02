package com.tamu.hackday.container.exception;

public enum ExceptionCodes {

	DB_POOLNAME_NOT_FOUND(101),
	ITEM_NOT_FOUND(208),
	DUPLICATE(209);
	
	private int statusCode;

	private ExceptionCodes(int s) {
		this.statusCode = s;
	}

	public int getStatusCode() {
		return statusCode;
	}
}
