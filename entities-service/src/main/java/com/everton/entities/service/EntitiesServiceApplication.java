package com.everton.entities.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
@RibbonClient(name = "entities-template-service", configuration = RibbonConfig.class)
public class EntitiesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntitiesServiceApplication.class, args);
	}

}
