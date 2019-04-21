package com.searchmetrics.xchangerate.service;


public enum HttpMethods {
	GET("GET"),
	POST("POST"),
	PUT("PUT"),
	DELETE("DELETE");

	private String method;

	private HttpMethods(String method) {
		this.method = method;
	}
	public String toString(){return method;}
	/**
	 * Constants only
	 */
}
