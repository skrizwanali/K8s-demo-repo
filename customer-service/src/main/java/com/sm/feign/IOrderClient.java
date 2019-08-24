package com.sm.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "order-service" /* , url = "http://localhost:8080" */)
public interface IOrderClient {

	@GetMapping("/order")
    ResponseEntity<String> getOrders();
}
