package com.sm.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;


@Component
@ConfigurationProperties(prefix="db")
@Data
public class PropertiesConfig {

	private String dbName;
	private String dbUser;
	private String dbPassword;
	@Value("${order.host}")
	private String orderHost;
	@Value("${order.port}")
	private String orderPort;
	@Value("${order.relPath}")
	private String orderRelPath;
	
}
