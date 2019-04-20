package com.searchmetrics.config;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.searchmetrics.controller.FieldSearchController;
import com.searchmetrics.xchangerate.controller.CurrencyExchangeRateController;




@Component
@ApplicationPath("/api/*")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(CurrencyExchangeRateController.class);
		
		register(FieldSearchController.class);
	}

	
}

