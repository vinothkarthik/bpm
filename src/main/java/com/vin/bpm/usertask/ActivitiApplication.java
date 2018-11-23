package com.vin.bpm.usertask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.vin.bpm.usertask.service.UserAssignmentService;


@SpringBootApplication
public class ActivitiApplication {

	@Autowired
	UserAssignmentService userAssignmentService;
	
	public static void main(String[] args) {
		SpringApplication.run(ActivitiApplication.class, args);  

	}
	
	@Bean
	public CommandLineRunner init(final UserAssignmentService myService) {

		return new CommandLineRunner() {
			public void run(String... strings) throws Exception {
				userAssignmentService.initiateUsers(new String[] {"csmi","csmr","wfl"});
			}
		};

	}

}
