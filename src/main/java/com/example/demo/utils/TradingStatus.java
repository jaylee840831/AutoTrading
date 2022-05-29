package com.example.demo.utils;

/*
 * 交易狀態類別
 * */

public enum TradingStatus {
	SUCCESS("success"),
	FAIL("fail");
	
	private String status;
	
	TradingStatus(String status) {
		this.status=status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
