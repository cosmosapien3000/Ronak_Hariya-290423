package com.avisys.cim;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerInfoManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerInfoManagementApplication.class, args);
	}

	// Model Mapper use to mapped between plane old java object with data transfer object  
		@Bean
		public ModelMapper modelMapper() {
			return new ModelMapper();
		}
}
