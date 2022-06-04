package com.example.demo.utils;

public class BingXUtils {

	public static String getUserId() {
		return BingXEnum.USERID.getInfo();
	}
	public static String getPlatForm() {
		return BingXEnum.PLATFORM.getInfo();
	}
	public static String getMessage() {
		return BingXEnum.MESSAGE.getInfo();
	}
	public static String getSymbol() {
		return BingXEnum.SYMBOL.getInfo();
	}
	public static String getApiKey() {
		return BingXEnum.APIKEY.getInfo();
	}
	public static String getSecretKey() {
		return BingXEnum.SECRETKEY.getInfo();
	}
	public static String getSide() {
		return BingXEnum.SIDE.getInfo();
	}
	public static String getEntrustPrice() {
		return BingXEnum.PRICE.getInfo();
	}
	public static String getEntrustVolume() {
		return BingXEnum.VOLUME.getInfo();
	}
	public static String getTradeType() {
		return BingXEnum.TRADETYPE.getInfo();
	}
	public static String getAction() {
		return BingXEnum.ACTION.getInfo();
	}
}
