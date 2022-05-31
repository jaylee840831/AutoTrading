package com.example.demo.utils;

public enum BingXEnum {
	USERID("userid"),PLATFORM("tradingPlatform"),MESSAGE("message"),SYMBOL("symbol"),APIKEY("apiKey"),TIME("timestamp"),
	SIDE("side"),PRICE("entrustPrice"),VOLUME("entrustVolume"),TRADETYPE("tradeType"),ACTION("action");

	private String info;
	
	BingXEnum(String info) {
		// TODO Auto-generated constructor stub
		this.info=info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
