package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserInfo;
import com.example.demo.utils.TradingPlatform;
import com.example.demo.utils.TradingStatus;
import com.example.demo.utils.TradingStatusUtils;


/*
 * 交易、使用者餘額資訊service
 * */

@Service
public class TradingService {
	
	@Autowired
	AutoTrading autoTrading;

	//自動交易
	public String autoTrading(String tradingPlatform) {
		return TradingStatusUtils.getSuccess();
	}
	
	//查詢餘額 USDT
	public String balance(UserInfo userInfo) {
		autoTrading.initUserInfo(userInfo);
		String result= autoTrading.getBalance();
		
		return result;
	}
}
