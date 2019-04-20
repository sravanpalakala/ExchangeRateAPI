package com.searchmetrics.xchangerate.api;


public class CurrencyNotSupportedException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CurrencyNotSupportedException() {
		super();
	}

	public CurrencyNotSupportedException(String message) {
		super(message);
	}

	public CurrencyNotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}

	public CurrencyNotSupportedException(Throwable cause) {
		super(cause);
	}
}
