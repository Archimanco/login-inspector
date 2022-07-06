package com.hotelbeds.supplierintegrations.hackertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class HacktestApplication {

	public static void main(String[] args) {
		SpringApplication.run(HacktestApplication.class, args);
	}

}
