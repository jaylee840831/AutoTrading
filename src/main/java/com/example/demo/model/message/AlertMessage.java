package com.example.demo.model.message;

import lombok.Data;

public class AlertMessage {

	private String username;
	private String tradingPlatform;
	private String message;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTradingPlatform() {
		return tradingPlatform;
	}
	public void setTradingPlatform(String tradingPlatform) {
		this.tradingPlatform = tradingPlatform;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "AlertMessage [username=" + username + ", tradingPlatform=" + tradingPlatform + ", message=" + message
				+ "]";
	} 
	
	
}
