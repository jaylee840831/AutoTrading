package com.example.demo.model.DTO;

import java.util.Date;

//告警訊息

public class BingXMessageDTO {

	private String userid;
	private String tradingPlatform;
	private String symbol;
	private String apiKey;
	private String secretKey;
	private String side;
	private Float entrustPrice;
	private Float entrustVolume;
	private String tradeType;
	private String action;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTradingPlatform() {
		return tradingPlatform;
	}
	public void setTradingPlatform(String tradingPlatform) {
		this.tradingPlatform = tradingPlatform;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public Float getEntrustPrice() {
		return entrustPrice;
	}
	public void setEntrustPrice(Float entrustPrice) {
		this.entrustPrice = entrustPrice;
	}
	public Float getEntrustVolume() {
		return entrustVolume;
	}
	public void setEntrustVolume(Float entrustVolume) {
		this.entrustVolume = entrustVolume;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	
}
