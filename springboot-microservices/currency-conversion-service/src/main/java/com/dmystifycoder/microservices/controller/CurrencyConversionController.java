package com.dmystifycoder.microservices.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dmystifycoder.microservices.entity.CurrencyConversion;
import com.dmystifycoder.microservices.proxy.CurrencyServiceProxy;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyServiceProxy proxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion retriveConvertedCurrency(@PathVariable String from,
												@PathVariable String to,
												@PathVariable BigDecimal quantity) {
		
		CurrencyConversion currencyConversion = proxy.retriveExchangeValue(from, to);
		currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
		currencyConversion.setQuantity(quantity);
		return currencyConversion;
		
	}

}
