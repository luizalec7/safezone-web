package com.safezone.web;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class SafezoneWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(SafezoneWebApplication.class, args);
	}
}

