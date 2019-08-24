package com.sm.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sm.config.PropertiesConfig;
import com.sm.feign.IOrderClient;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WebServiceClient {
	
	@Autowired
	private IOrderClient orderClient;
	
	@Autowired
	PropertiesConfig propertiesConfig;

	public String  invokeOrderService() {
		RestTemplate restTemplate = new RestTemplate();
		
		String host = propertiesConfig.getOrderHost();
		String port = propertiesConfig.getOrderPort();
		String path = propertiesConfig.getOrderRelPath();
		String orderUrl = "http://"+host+":"+port+""+path;
		
		log.info("orderUrl >>>>>>>>>>>>>>> :{}", orderUrl);
		ResponseEntity<String> response
				= restTemplate.getForEntity(orderUrl, String.class);
		return response.getBody();
		
	}
	
	public String  invokeOrderServiceThruFeign() {
		
		ResponseEntity<String> response = orderClient.getOrders();
		return response.getBody();
		
	}

}
