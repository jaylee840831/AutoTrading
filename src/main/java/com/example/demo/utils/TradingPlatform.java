package com.example.demo.utils;

/*
 * 交易平台類別
 * */

public enum TradingPlatform {
	BINGX("BingX");
	
	private String tradingPlatform;
	
	TradingPlatform(String tradingPlatform){
		this.tradingPlatform=tradingPlatform;
	}

	public String getTradingPlatform() {
		return tradingPlatform;
	}

	public void setTradingPlatform(String tradingPlatform) {
		this.tradingPlatform = tradingPlatform;
	}
	
}
