package com.example.demo.controller;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.model.UserInfo;
import com.example.demo.model.message.AlertMessage;
import com.example.demo.service.TradingService;
import com.example.demo.utils.LogUtils;
import com.example.demo.utils.TradingPlatformUtils;
import com.example.demo.utils.TradingStatusUtils;

@RestController
@RequestMapping(method = RequestMethod.GET,value="/api/v1")
public class ApiController {
	
	@Autowired
	TradingService tradingService;
	
	Logger bussinessLog=LogUtils.getBussinessLogger();

	@PostMapping(value="/webhook")
	@ResponseBody
	public ResponseEntity<AlertMessage>getTradingViewAlertMessage(AlertMessage message){
		
		if(message==null) {
			bussinessLog.info("trading info: {} trading status: {}",message,TradingStatusUtils.getFail());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorTradingInfoMessage());
		}
		
		if(message.getTradingPlatform().equals(TradingPlatformUtils.getBingX())) {
			// 自動化交易
			String tradingStatus= tradingService.autoTrading(TradingPlatformUtils.getBingX());
			bussinessLog.info("trading info: {} trading status: {}",message,tradingStatus);	
		}
		else {
			bussinessLog.info("trading info: {} trading status: {}",message,TradingStatusUtils.getFail());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorTradingInfoMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
	public AlertMessage errorTradingInfoMessage() {
		AlertMessage errorMessage=new AlertMessage();
		errorMessage.setUsername("error user");
		errorMessage.setTradingPlatform("error trading platform");
		errorMessage.setMessage("error request");
		
		return errorMessage;
	}
}
