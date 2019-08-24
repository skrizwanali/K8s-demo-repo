package com.sm.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm.config.PropertiesConfig;
import com.sm.utils.WebServiceClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CustomerController {
	@Autowired
	PropertiesConfig propertiesConfig;
	@Autowired
	WebServiceClient webServiceClient;
	
	@GetMapping("/")
	public ResponseEntity<String> test(){
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping("/customer")
	public String getOrders() throws URISyntaxException, IOException {
		final URI userUri = ResourceUtils.getURL(propertiesConfig.getDbUser()).toURI();
		final URI passUri = ResourceUtils.getURL(propertiesConfig.getDbPassword()).toURI();
		final byte[] encodedUser = Files.readAllBytes(Paths.get(userUri));
        final byte[] encodedPasas = Files.readAllBytes(Paths.get(passUri));
        
        String msg = webServiceClient.invokeOrderServiceThruFeign();
        		//.invokeOrderService();
        log.info("Message from order service :{}", msg);

        String user = sanitize(encodedUser);
        String pass = sanitize(encodedPasas);
		log.info("user from yml :{}", user);
		log.info("pass from yml :{}", pass);
		log.info("dbName from yml :{}", propertiesConfig.getDbName());
		
		return "Hi, this is customer service..Message from order service :"+msg;
		
	//	return "DB name :"+propertiesConfig.getDbName()+" | DB user :"+user+" | DB pass :"+pass+" |v2.0";
	}
	
	private String sanitize(byte[] strBytes) {
        return new String(strBytes)
            .replace("\r", "")
            .replace("\n", "");
    }
	
}
