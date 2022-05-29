package com.example.demo.controller;

import org.apache.logging.log4j.Logger;
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

	@GetMapping("/main")
	public String main(Model model) {
		return "main";
	}
	
	@PostMapping(value="/main")
	public String getBalance(UserInfo userInfo,Model model){
		
		if(userInfo==null) {
			bussinessLog.info("user info: bad user info request");
			model.addAttribute("balanceInfo", "error UserInfo Message");
		}
		
		//取得用戶餘額資訊
		String result= tradingService.balance(userInfo);
		
		model.addAttribute("balanceInfo", result);
		
		return "main";
	}
	
}
