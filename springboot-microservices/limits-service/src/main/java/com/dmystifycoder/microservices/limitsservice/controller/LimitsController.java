package com.dmystifycoder.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmystifycoder.microservices.limitsservice.configurations.Configurations;
import com.dmystifycoder.microservices.limitsservice.entity.Limits;

@RestController
public class LimitsController {
	
	@Autowired
	private Configurations config;
	
	@GetMapping("/limits")
	public Limits retrieveLimits() {
		return new Limits(config.getMinimum(),
						config.getMaximum());
	}

}
