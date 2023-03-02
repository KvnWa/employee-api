package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class EmployeeUiKevinWangApplication {
	
	private final String BASE_URL = "http://localhost:8081";
	

	public static void main(String[] args) {
		SpringApplication.run(EmployeeUiKevinWangApplication.class, args);
	}
	
	@Bean
	public WebClient.Builder builder(){
		return WebClient.builder();
	}
	
	@Bean
	public WebClient webclient(WebClient.Builder builder) {
		return builder.baseUrl(BASE_URL)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

}
