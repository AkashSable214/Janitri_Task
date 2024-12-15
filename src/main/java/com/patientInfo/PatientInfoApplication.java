package com.patientInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.patientInfo")
@EnableJpaRepositories(basePackages = "com.patientInfo.repo")
public class PatientInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientInfoApplication.class, args);
	}

}
