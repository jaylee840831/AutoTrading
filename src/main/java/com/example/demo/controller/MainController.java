package com.example.demo.controller;

import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.UserInfo;
import com.example.demo.service.TradingService;
import com.example.demo.utils.LogUtils;

@Controller
public class MainController {
	
	@Autowired
	TradingService tradingService;
	
	Logger bussinessLog=LogUtils.getBussinessLogger();
	Logger exceptionLog=LogUtils.getExceptionLogger();

	@GetMapping("/main")
	public String main(Model model) {
		return "main";
	}
	
	@PostMapping(value="/main")
	public String getBalance(UserInfo userInfo,Model model){
		
		if(userInfo.getApiKey().equals("") || userInfo.getSecretKey().equals("")) {
			bussinessLog.info("user info: bad user info request");
			model.addAttribute("balanceInfo", "Please Input Complete User Information");
		}
		else {
			//取得用戶餘額資訊
			String result= tradingService.balance(userInfo);
			model.addAttribute("balanceInfo", resultFormat(result).toString());
		}
		
		return "main";
	}
	
	public StringBuilder resultFormat(String result) {
		
		StringBuilder result2 = new StringBuilder();
		
		try {
		     JSONObject jsonObject = new JSONObject(result);
		     jsonObject.keySet().forEach(keyStr ->
		     {
		    	 if(keyStr.equals("data")) {
		    		JSONObject data = (JSONObject) jsonObject.get(keyStr);
		    		
		    		if(!data.isEmpty()) {
			    		for (String key : data.keySet()) {
			    			if(key.equals("account")) {
			    				JSONObject account = (JSONObject) data.get(key);
			    					for (String k : account.keySet()) {
						    			Object keyvalue = account.get(k);
						    			switch (k) {
										case "userId": {
											result2.append("用戶ID: "+keyvalue.toString());
											result2.append(System.getProperty("line.separator"));
											break;
										}
										case "currency": {
											result2.append("用戶資產: "+keyvalue.toString());
											result2.append(System.getProperty("line.separator"));
											break;
										}
										case "balance": {
											result2.append("資產餘額: "+keyvalue.toString());
											result2.append(System.getProperty("line.separator"));
											break;
										}
										case "equity": {
											result2.append("資產凈值: "+keyvalue.toString());
											result2.append(System.getProperty("line.separator"));
											break;
										}
										case "unrealisedPNL": {
											result2.append("未實現盈虧: "+keyvalue.toString());
											result2.append(System.getProperty("line.separator"));
											break;
										}
										case "realisedPNL": {
											result2.append("已實現盈虧: "+keyvalue.toString());
											result2.append(System.getProperty("line.separator"));
											break;
										}
										case "availableMargin": {
											result2.append("可用保證金: "+keyvalue.toString());
											result2.append(System.getProperty("line.separator"));
											break;
										}
										case "usedMargin": {
											result2.append("已用保證金: "+keyvalue.toString());
											result2.append(System.getProperty("line.separator"));
											break;
										}
										case "freezedMargin": {
											result2.append("凍結保證金: "+keyvalue.toString());
											result2.append(System.getProperty("line.separator"));
											break;
										}
										case "longLeverage": {
											result2.append("做多槓桿: "+keyvalue.toString());
											result2.append(System.getProperty("line.separator"));
											break;
										}
										case "shortLeverage": {
											result2.append("做空槓桿: "+keyvalue.toString());
											result2.append(System.getProperty("line.separator"));
											break;
										}
										default:
											break;
										}
			    					}
			    			}
			    		}
		    		}
		    	 }
		     });
		}catch (JSONException err){
			exceptionLog.info("string parse to json exception: {}",err);
		}
		
		return result2;
	}
	
}
