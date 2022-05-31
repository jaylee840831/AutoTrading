package com.example.demo.model.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//面向資料庫的告警訊息物件

@Entity
@Table(name = "message")
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class BingXMessageDAO {

	@Id
	private String userid;
	
	@Column(name="trading_platform")
	private String tradingPlatform;
	
	@Column(name="symbol")
	private String symbol;
	
	@Column(name = "api_key")
	private String apiKey;
	
	@Column(name = "timestamp")
	private String timestamp;
	
	@Column(name = "side")
	private String side;
	
	@Column(name="entrust_price")
	private Float entrustPrice;
	
	@Column(name = "entrust_volume")
	private Float entrustVolume;
	
	@Column(name = "trade_type")
	private String tradeType;
	
	@Column(name = "action")
	private String action;

	public BingXMessageDAO(String userid, String tradingPlatform, String symbol, String apiKey, String timestamp,
			String side, Float entrustPrice, Float entrustVolume, String tradeType, String action) {
		super();
		this.userid = userid;
		this.tradingPlatform = tradingPlatform;
		this.symbol = symbol;
		this.apiKey = apiKey;
		this.timestamp = timestamp;
		this.side = side;
		this.entrustPrice = entrustPrice;
		this.entrustVolume = entrustVolume;
		this.tradeType = tradeType;
		this.action = action;
	}

	public BingXMessageDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
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

	@Override
	public String toString() {
		return "BingXMessageDAO [userid=" + userid + ", tradingPlatform=" + tradingPlatform + ", symbol=" + symbol
				+ ", apiKey=" + apiKey + ", timestamp=" + timestamp + ", side=" + side + ", entrustPrice="
				+ entrustPrice + ", entrustVolume=" + entrustVolume + ", tradeType=" + tradeType + ", action=" + action
				+ "]";
	}
	
	
}
