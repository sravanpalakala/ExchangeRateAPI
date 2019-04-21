package com.searchmetrics.config;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.searchmetrics.xchangerate.controller.CurrencyExchangeRateController;

@SpringBootApplication(scanBasePackages = {"com.searchmetrics"})
@ComponentScan(basePackageClasses = CurrencyExchangeRateController.class)
public class SpringBootRestApplication {
	 public static void main(String[] args) {
         SpringApplication.run(SpringBootRestApplication.class, args);
  }
	/* @Bean
	    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	        return args -> {

	            System.out.println("Let's inspect the beans provided by Spring Boot:"+ctx.getApplicationName());
	            
            
	            String[] beanNames = ctx.getBeanDefinitionNames();
	            Arrays.sort(beanNames);
	            for (String beanName : beanNames) {
	                System.out.println(beanName);
	            }

	        };
	    }*/
}
