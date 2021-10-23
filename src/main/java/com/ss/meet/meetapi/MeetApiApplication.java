package com.ss.meet.meetapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MeetApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetApiApplication.class, args);
	}

}
