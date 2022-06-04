package com.example.demo.controller;

import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.TradingService;
import com.example.demo.service.UserService;
import com.example.demo.utils.LogUtils;

@Controller
public class MainController {
	
	@Autowired
	TradingService tradingService;
	
	@Autowired
	UserService userService;
	
	Logger bussinessLog=LogUtils.getBussinessLogger();
	Logger exceptionLog=LogUtils.getExceptionLogger();
	
	@GetMapping("/")
	public String welcomeView(Model model) {
		return "login";
	}
	
	@GetMapping("/login")
	public String loginView(Model model) {
		return "login";
	}

	@GetMapping("/BingX")
	public String mainView(Authentication authentication,Model model) {
		String username="";
		// 取得user的資訊
		if(authentication!=null) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			//取得user的權限種類
//			System.out.println("User has authorities: " + userDetails.getAuthorities());
			//取得user的名稱
			username=userDetails.getUsername();
		}
		
		Map<String, String>result=new TreeMap<String, String>();
		
		if(username!=null || !username.equals("")) {
			try {
				//取得用戶餘額資訊
				String response= tradingService.BingXBalance(userService.getUserInfo(username));
				model.addAttribute("balanceInfo", BingXResultToMap(response,result));
				bussinessLog.info("balance info: {}",BingXResultToMap(response,result));
			} catch (Exception e) {
				bussinessLog.info("balance info: bad request ,{}",e);
				result.put("error", e.toString());
				model.addAttribute("balanceInfo", result);
			}
		}
		else {
			bussinessLog.info("balance info: bad request");
			result.put("error", "Get User Information Error");
			model.addAttribute("balanceInfo", result);
		}
		
		return "BingX";
	}
	
//	@PostMapping(value="/BingX")
//	public String getBingXBalance(BingX userInfo,Model model){
//		
//		if(userInfo.getApiKey().equals("") || userInfo.getSecretKey().equals("")) {
//			bussinessLog.info("user info: bad user info request");
//			model.addAttribute("balanceInfo", "Please Input Complete User Information");
//		}
//		else {
//			//取得用戶餘額資訊
//			String result= tradingService.BingXBalance(userInfo);
//			model.addAttribute("balanceInfo", BingXResultFormat(result).toString());
//		}
//		
//		return "BingX";
//	}
	
	@GetMapping(value="/BingX_message")
	public String messageView() {
		return "BingX_message";
	}
	
	//解析BingX回傳的使用者資訊，並整合成map
	public Map<String, String> BingXResultToMap(String result,Map<String, String>result2) {
		
		try {
			JSONObject jsonObject=new JSONObject(result);
			
			if(jsonObject.length()!=0) {
			     jsonObject.keySet().forEach(keyStr ->
			     {
			    	 if(keyStr.equals("data")) {
			    		JSONObject data = (JSONObject) jsonObject.get(keyStr);
			    		
			    		if(data.length()!=0) {
				    		for (String key : data.keySet()) {
				    			if(key.equals("account")) {
				    				JSONObject account = (JSONObject) data.get(key);
				    					for (String k : account.keySet()) {
							    			Object keyvalue = account.get(k);
							    			switch (k) {
											case "userId": {
												result2.put("用戶ID",keyvalue.toString());
												break;
											}
											case "currency": {
												result2.put("用戶資產",keyvalue.toString());
												break;
											}
											case "balance": {
												result2.put("資產餘額",keyvalue.toString());
												break;
											}
											case "equity": {
												result2.put("資產淨值",keyvalue.toString());
												break;
											}
											case "unrealisedPNL": {
												result2.put("未實現盈虧",keyvalue.toString());
												break;
											}
											case "realisedPNL": {
												result2.put("已實現盈虧",keyvalue.toString());
												break;
											}
											case "availableMargin": {
												result2.put("可用保證金",keyvalue.toString());
												break;
											}
											case "usedMargin": {
												result2.put("已用保證金",keyvalue.toString());
												break;
											}
											case "freezedMargin": {
												result2.put("凍結保證金",keyvalue.toString());												
												break;
											}
											case "longLeverage": {
												result2.put("做多槓桿",keyvalue.toString());
												break;
											}
											case "shortLeverage": {
												result2.put("做空槓桿",keyvalue.toString());
												break;
											}
											default:
												break;
											}
				    					}
				    			}
				    		}
			    		}
			    		else {
			    			result2.put("error","No User Information , Please Check Your Key is Correct");
			    		}
			    	 }
			     });
			}else {
				result2.put("error","No User Information , Please Check Your Key is Correct");
    		}
		}catch (JSONException err){
			exceptionLog.info("user info exception: {}",err);
			result2.put("error",err.toString());
		}
		
		return result2;
	}
	
}
