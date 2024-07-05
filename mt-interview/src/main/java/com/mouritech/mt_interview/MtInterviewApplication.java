package com.mouritech.mt_interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MtInterviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtInterviewApplication.class, args);
	}

}
