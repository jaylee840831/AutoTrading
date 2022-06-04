package com.example.demo.controller;

import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ErrorMessage;
import com.example.demo.model.DTO.BingXMessageDTO;
import com.example.demo.service.TradingService;
import com.example.demo.utils.BingXUtils;
import com.example.demo.utils.LogUtils;
import com.example.demo.utils.TradingPlatformUtils;

@RestController
@RequestMapping(method = RequestMethod.GET,value="/api/v1")
public class ApiController {
	
	@Autowired
	TradingService tradingService;
	
	Logger bussinessLog=LogUtils.getBussinessLogger();

	// 接收tradingview的webhook url發送的交易訊息，再經由指定的平台交易
	@PostMapping(value="/webhook/{platform}")
	public void getTradingViewAlertMessage(@RequestBody String m,@PathVariable("platform")String platform){
		
		JSONObject message = new JSONObject(m);
		
		// 以BingX交易
		if(platform.equals(TradingPlatformUtils.getBingX())) {
			BingXMessageDTO bingXMessageDTO=new BingXMessageDTO();
			bingXMessageDTO.setTradingPlatform(platform);
			
		    message.keySet().forEach(keyStr ->
		    {
				if(keyStr.equals(BingXUtils.getUserId())) {
					bingXMessageDTO.setUserid(message.get(keyStr).toString());
				}
				else if(keyStr.equals(BingXUtils.getMessage())) {
					
		    		JSONObject messageObject =(JSONObject) message.get(keyStr);
		    		for (String key : messageObject.keySet()) {
		    			if(key.equals(BingXUtils.getSymbol())) {
		    				bingXMessageDTO.setSymbol(messageObject.get(key).toString());
		    			}
		    			else if(key.equals(BingXUtils.getApiKey())) {
		    				bingXMessageDTO.setApiKey(messageObject.get(key).toString());
		    			}
		    			else if(key.equals(BingXUtils.getSecretKey())) {
		    				bingXMessageDTO.setSecretKey(messageObject.get(key).toString());
		    			}
		    			else if(key.equals(BingXUtils.getSide())) {
		    				bingXMessageDTO.setSide(messageObject.get(key).toString());
		    			}
		    			else if(key.equals(BingXUtils.getEntrustPrice())) {
		    				bingXMessageDTO.setEntrustPrice(Float.valueOf(messageObject.get(key).toString()));
		    			}
		    			else if(key.equals(BingXUtils.getEntrustVolume())) {
		    				bingXMessageDTO.setEntrustVolume(Float.valueOf(messageObject.get(key).toString()));
		    			}
		    			else if(key.equals(BingXUtils.getTradeType())) {
		    				bingXMessageDTO.setTradeType(messageObject.get(key).toString());
		    			}
		    			else if(key.equals(BingXUtils.getAction())) {
		    				bingXMessageDTO.setAction(messageObject.get(key).toString());
		    			}
		    		}			    		
				}
		    	
		    });
		    
			boolean tradingStatus= tradingService.BingXTrading(bingXMessageDTO);
			
			if(tradingStatus==false) {
				bussinessLog.info("trading info: {} trading status: Fail",bingXMessageDTO.toString());
			}
			else {
				bussinessLog.info("trading info: {} trading status: Success",bingXMessageDTO.toString());
			}
		}
		else {
			bussinessLog.info("trading info: {} trading status: Fail",errorMessage("bad request,please check your URL"));
		}
	}
	
	public ErrorMessage errorMessage(String str) {
		ErrorMessage errorMessage=new ErrorMessage(str);
		return errorMessage;
	}
}
