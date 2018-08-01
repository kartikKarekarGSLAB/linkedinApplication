package com.gslab.linkedin.linkedindemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;

import com.gslab.linkedin.linkedindemo.property.FileStorageProperties;

@SpringBootApplication
@ImportResource(locations = { "classpath:/config/xml/application-config.xml" })
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class LinkedInDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkedInDemoApplication.class, args);
	}
}
