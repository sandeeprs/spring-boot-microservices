package com.dmystifycoder.microservices.configuration;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	
	
	
	/*
	 * To prepare custtom routes for api gateway 
	 * http://localhost:8765/currency-exchange/currency-exchange/from/USD/to/INR 
	 * http://localhost:8765/currency-exchange/from/USD/to/INR
	 * Matching the incoming pattern 'currency-exchange' and redirecting it to namingserver/loadbalancer
	 * using the registered name of the service on naming server 'lb://currency-exchange' 
	 * where lb = load balancer and 'currency-exchange' is the registered name of the microservice on eureka naming server
	 */
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f
								.addRequestHeader("My-name", "sandeep")
								.addRequestParameter("param", "123"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/currency-exchange/**")
						.uri("lb://currency-exchange"))
				.route(p -> p.path("/currency-conversion/**")
						.uri("lb://currency-conversion"))
				.build();
	}
	
}
