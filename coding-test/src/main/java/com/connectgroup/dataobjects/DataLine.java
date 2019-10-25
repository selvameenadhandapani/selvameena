package com.connectgroup.dataobjects;

import java.util.Date;

public class DataLine {
	private String strCountry;
	private long responseTimeInms;
	private String requestTimestamp;
	
	public String getStrCountry() {
		return strCountry;
	}
	public void setStrCountry(String strCountry) {
		this.strCountry = strCountry;
	}
	public long getResponseTimeInms() {
		return responseTimeInms;
	}
	public void setResponseTimeInms(long responseTimeInms) {
		this.responseTimeInms = responseTimeInms;
	}
	public String getRequestTimestamp() {
		return requestTimestamp;
	}
	public void setRequestTimestamp(String requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	} 
}