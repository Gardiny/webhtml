package com.softskills.softskills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SoftskillsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftskillsApplication.class, args);
	}

}
