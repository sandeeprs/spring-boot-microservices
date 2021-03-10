package com.dmystifycoder.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dmystifycoder.microservices.currencyexchangeservice.entity.CurrencyExchange;
import com.dmystifycoder.microservices.currencyexchangeservice.repository.CurrencyExchangeRepo;

@RestController
public class CurrencyExchangeController {
	
	Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CurrencyExchangeRepo repo;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchangeValue(@PathVariable String from, 
											@PathVariable String to) {
		
		logger.info("Inside Currency Exchange Controller ");
		
		String envProperty = env.getProperty("local.server.port");
		CurrencyExchange currencyExchange = repo.findByFromAndTo(from, to);
		
		if(currencyExchange == null) {
			throw new RuntimeException("Unable to find data for "+ from + " and " +to);
		}
		
		currencyExchange.setEnvironment(envProperty);
		
		return currencyExchange;
		
		
		
	}

}
