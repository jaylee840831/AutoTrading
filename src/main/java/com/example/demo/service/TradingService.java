package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.utils.TradingPlatform;
import com.example.demo.utils.TradingStatus;
import com.example.demo.utils.TradingStatusUtils;

@Service
public class TradingService {

	//自動交易
	public String autoTrading(String tradingPlatform) {
		return TradingStatusUtils.getSuccess();
	}
}
