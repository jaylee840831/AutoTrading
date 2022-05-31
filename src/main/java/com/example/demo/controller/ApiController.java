package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.example.demo.model.DAO.BingXMessageDAO;
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

	@PostMapping(value="/webhook/{platform}")
	public void getTradingViewAlertMessage(@RequestBody String m,@PathVariable("platform")String platform){
		
		Map<String, Object> result =null;
		JSONObject message = new JSONObject(m);
		
//		if(message.length()==0) {
//			bussinessLog.info("trading info: {} trading status: {}",message,TradingStatusUtils.getFail());
//			result = new HashMap<String, Object>()
//				{
//					private static final long serialVersionUID = 1L;
//
//					{
//				        put(platform,errorMessage("bad request,please check tradingview's alert message setting"));
//				    }
//				};
//				bussinessLog.info("trading info: {} trading status: {}",result,TradingStatusUtils.getFail());
//		}
		
		// 自動化交易
		if(platform.equals(TradingPlatformUtils.getBingX())) {
			BingXMessageDAO bingXMessageDAO=new BingXMessageDAO();
			bingXMessageDAO.setTradingPlatform(platform);
			
		    message.keySet().forEach(keyStr ->
		    {
				if(keyStr.equals(BingXUtils.getUserId())) {
					bingXMessageDAO.setUserid(message.get(keyStr).toString());
				}
				else if(keyStr.equals(BingXUtils.getMessage())) {
					
		    		JSONObject messageObject =(JSONObject) message.get(keyStr);
		    		for (String key : messageObject.keySet()) {
		    			if(key.equals(BingXUtils.getSymbol())) {
		    				bingXMessageDAO.setSymbol(messageObject.get(key).toString());
		    			}
		    			else if(key.equals(BingXUtils.getApiKey())) {
		    				bingXMessageDAO.setApiKey(messageObject.get(key).toString());
		    			}
		    			else if(key.equals(BingXUtils.getTimestamp())) {
		    				bingXMessageDAO.setTimestamp(messageObject.get(key).toString());
		    			}
		    			else if(key.equals(BingXUtils.getSide())) {
		    				bingXMessageDAO.setSide(messageObject.get(key).toString());
		    			}
		    			else if(key.equals(BingXUtils.getEntrustPrice())) {
		    				bingXMessageDAO.setEntrustPrice(Float.valueOf(messageObject.get(key).toString()));
		    			}
		    			else if(key.equals(BingXUtils.getEntrustVolume())) {
		    				bingXMessageDAO.setEntrustVolume(Float.valueOf(messageObject.get(key).toString()));
		    			}
		    			else if(key.equals(BingXUtils.getTradeType())) {
		    				bingXMessageDAO.setTradeType(messageObject.get(key).toString());
		    			}
		    			else if(key.equals(BingXUtils.getAction())) {
		    				bingXMessageDAO.setAction(messageObject.get(key).toString());
		    			}
		    		}			    		
				}
		    	
		    });
		    
			boolean tradingStatus= tradingService.BingXTrading(bingXMessageDAO);
			if(tradingStatus==false) {
				result = new HashMap<String, Object>()
				{
					private static final long serialVersionUID = 1L;

					{
				        put("user id "+bingXMessageDAO.getUserid(),errorMessage("交易資料可能存在空值或是不正確導致交易失敗"));
				    }
				};
				bussinessLog.info("trading info: {} trading status: Fail",result);
			}
			else {
				bussinessLog.info("trading info: {} trading status: Success",bingXMessageDAO);
			}
		}
		else {
			result = new HashMap<String, Object>()
			{
				private static final long serialVersionUID = 1L;

				{
			        put("platform "+platform,errorMessage("bad request,please check your URL"));
			    }
			};
			bussinessLog.info("trading info: {} trading status: Fail",result);
		}
	}
	
	public ErrorMessage errorMessage(String str) {
		ErrorMessage errorMessage=new ErrorMessage(str);
		return errorMessage;
	}
}
