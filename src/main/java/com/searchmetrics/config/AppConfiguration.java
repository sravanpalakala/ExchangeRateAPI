package com.searchmetrics.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;


@ComponentScan(value={"com.atfarm"})
public class AppConfiguration {
	@Autowired
    ConfigurableEnvironment env; 
	
	
	// commented  added line  build now for jenkins
	
	
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
	  webServerFactoryCustomizer() {
	    return factory -> factory.setContextPath("/XchangeApp");
	}
}
