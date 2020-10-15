package com.application.springbootjpademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SpringBootJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaDemoApplication.class, args);
	}

}
