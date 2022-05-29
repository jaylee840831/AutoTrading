package com.example.demo.controller;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.message.AlertMessage;
import com.example.demo.service.TradingService;
import com.example.demo.utils.LogUtils;
import com.example.demo.utils.TradingPlatformUtils;

@RestController
@RequestMapping(method = RequestMethod.GET,value="/api")
public class ApiController {
	
	@Autowired
	TradingService tradingService;
	
	Logger bussinessformLog=LogUtils.getBussinessLogger();

	@PostMapping(value="/webhook")
	@ResponseBody
	public ResponseEntity<AlertMessage>getTradingViewAlertMessage(@RequestBody(required = false) AlertMessage message){
		
		if(message==null) {
			bussinessformLog.info("trading info: bad trading info request");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage());
		}
		
		
		if(message.getTradingPlatform().equals(TradingPlatformUtils.getBingX())) {
			// 自動化交易
			String tradingStatus= tradingService.autoTrading(TradingPlatformUtils.getBingX());
			bussinessformLog.info("trading info: {} trading status: {}",message,tradingStatus);
		}
		else {
			bussinessformLog.info("trading info: bad trading info request(trading platform not found) {}",message);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
	public AlertMessage errorMessage() {
		AlertMessage errorMessage=new AlertMessage();
		errorMessage.setUsername("error user");
		errorMessage.setTradingPlatform("error trading platform");
		errorMessage.setMessage("error request");
		
		return errorMessage;
	}
}
