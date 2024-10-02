package com.api4;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Registration4Application {

	public static void main(String[] args) {
		SpringApplication.run(Registration4Application.class, args);
	}
@Bean
	public ModelMapper getMp(){
		return new ModelMapper();
}
}
