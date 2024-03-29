package com.example.demo.model.DAO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//面向資料庫的告警訊息

@Entity
@Table(name = "bingx_message")
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class BingXMessageDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="userid")
	private String userid;
	
	@Column(name="trading_platform")
	private String tradingPlatform;
	
	@Column(name="symbol")
	private String symbol;
	
	@Column(name = "date")
	private Date date;
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
