package com.example.spring.vaadin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.example.spring.vaadin")
public class SpringVaadinApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringVaadinApplication.class, args);
	}
}
