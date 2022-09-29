package com.tstcore.appinvestecclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppInvestecClient {

	public static void main(String[] args) {
		SpringApplication.run(AppInvestecClient.class, args);
		System.out.println("Investec client running");
	}
}
