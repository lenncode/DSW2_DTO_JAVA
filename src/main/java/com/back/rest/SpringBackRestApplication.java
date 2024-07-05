package com.back.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringBackRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackRestApplication.class, args);
		System.out.println("FUERZAAAAAAAAAAAAAAAAA");
	}

}
