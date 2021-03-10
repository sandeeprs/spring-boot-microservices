package com.dmystifycoder.microservices.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dmystifycoder.microservices.entity.CurrencyConversion;

//Use the spring.application.name of the microservice that needs to be called
//@FeignClient(name = "currency-exchange", url = "localhost:8000")

//Removing url after registering services on eureka for load balancing so that multiple instances can be called
//running on port 8000 or 8001 or 8002 etc
@FeignClient(name = "currency-exchange")
public interface CurrencyServiceProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retriveExchangeValue(@PathVariable String from, 
											@PathVariable String to);

}
