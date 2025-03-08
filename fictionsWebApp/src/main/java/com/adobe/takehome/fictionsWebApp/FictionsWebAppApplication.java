package com.adobe.takehome.fictionsWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.adobe.takehome.fictionsWebApp"})
public class FictionsWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FictionsWebAppApplication.class, args);
	}

}
