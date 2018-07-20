package com.gslab.linkedin.linkedindemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations= {"classpath:/config/xml/application-config.xml"})
public class LinkedInDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkedInDemoApplication.class, args);
	}
}
