package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.DAO.BingXUserDAO;
import com.example.demo.model.DTO.BingXMessageDTO;


/*
 * BingX交易、使用者餘額資訊service
 * */

@Service
public class TradingService {

	//BingX平台交易
	public boolean BingXTrading(BingXMessageDTO message) {
		BingXTrading bingXTrading=new BingXTrading(message.getApiKey(),message.getSecretKey());
		if(checkEmpty(message)==true) {
			return false;
		}
		
		return bingXTrading.placeOrder(message.getSymbol(), message.getSide(), 
				String.valueOf(message.getEntrustPrice()), String.valueOf(message.getEntrustVolume()), 
				message.getTradeType(), message.getAction());
	}
	
	//BingX平台查詢用戶資訊
	public String BingXBalance(BingXUserDAO userInfo) {
		BingXTrading bingXTrading=new BingXTrading(userInfo);
		return bingXTrading.getBalance();
	}
	
	public boolean checkEmpty(BingXMessageDTO message) {
		if(message.getUserid().equals("") || message.getTradingPlatform().equals("") || message.getSymbol().equals("") || 
				message.getApiKey().equals("") || message.getSecretKey().equals("") ||  message.getSide().equals("") ||
				message.getEntrustPrice()==null || message.getEntrustVolume()==null || message.getTradeType().equals("") ||
				message.getAction().equals("")) {
			return true;
		}
		
		return false;
	}
}
