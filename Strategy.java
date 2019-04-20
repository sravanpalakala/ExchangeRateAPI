package com.searchmetrics.xchangerate.util;


public enum Strategy {
	
	CURRENCY_LAYER_FILESTORE("CURRENCY_LAYER_FILESTORE"),
	
	CURRENCY_LAYER_HISTORICAL_FILESTORE("CURRENCY_LAYER_HISTORICAL_FILESTORE"),
	
	CURRENCY_LAYER_CONVERTER_FILESTORE("CURRENCY_LAYER_CONVERTER_FILESTORE");
	

	private final String strategy;

	private Strategy(String strategy) {
		this.strategy = strategy;
	}

	public String toString() {
		return this.strategy;
	}
	/**
	 * Constants only
	 */
}
