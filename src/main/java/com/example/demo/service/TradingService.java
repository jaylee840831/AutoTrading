package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BingX;
import com.example.demo.utils.TradingStatusUtils;


/*
 * 交易、使用者餘額資訊service
 * */

@Service
public class TradingService {
	
	@Autowired
	BingXTrading bingXTrading;

	//自動交易
	public String autoTrading(String tradingPlatform) {
		return TradingStatusUtils.getSuccess();
	}
	
	//查詢餘額 USDT
	public String balance(BingX userInfo) {
		bingXTrading.initUserInfo(userInfo);
		String result= bingXTrading.getBalance();
		
		return result;
	}
}
