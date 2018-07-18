package com.gslab.linkedin.LINKEDINDEMO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations= {"classpath:/config/xml/application-config.xml"})
public class LinkedindemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkedindemoApplication.class, args);
	}
}
