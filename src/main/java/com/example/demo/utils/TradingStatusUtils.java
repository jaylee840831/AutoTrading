package com.example.demo.utils;

/*
 * 取得交易狀態類別
 * */

public class TradingStatusUtils {
	
	public static String getSuccess() {
		return TradingStatus.SUCCESS.getStatus();
	}
	
	public static String getFail() {
		return TradingStatus.FAIL.getStatus();
	}

}
