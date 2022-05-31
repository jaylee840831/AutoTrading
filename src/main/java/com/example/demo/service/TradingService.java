package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BingX;
import com.example.demo.model.DAO.BingXMessageDAO;


/*
 * 交易、使用者餘額資訊service
 * */

@Service
public class TradingService {

	//BingX平台交易
	public boolean BingXTrading(BingXMessageDAO message) {
		BingXTrading bingXTrading=new BingXTrading();
		if(checkEmpty(message)==true || message.getApiKey().equals("")) {
			return false;
		}

		bingXTrading.initTradingInfo(message.getApiKey());
		
		return bingXTrading.placeOrder(message.getSymbol(), message.getSide(), 
				String.valueOf(message.getEntrustPrice()), String.valueOf(message.getEntrustVolume()), 
				message.getTradeType(), message.getAction());
	}
	
	//BingX平台查詢用戶資訊
	public String BingXBalance(BingX userInfo) {
		BingXTrading bingXTrading=new BingXTrading();
		bingXTrading.initUserInfo(userInfo);
		String result= bingXTrading.getBalance();
		
		return result;
	}
	
	public boolean checkEmpty(BingXMessageDAO message) {
		if(message.getUserid().equals("") || message.getTradingPlatform().equals("") || message.getSymbol().equals("") || 
				message.getApiKey().equals("") || message.getTimestamp().equals("") || message.getSide().equals("") ||
				message.getEntrustPrice()==null || message.getEntrustVolume()==null || message.getTradeType().equals("") ||
				message.getAction().equals("")) {
			return true;
		}
		
		return false;
	}
}
