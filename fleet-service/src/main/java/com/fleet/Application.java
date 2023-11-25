package com.fleet;

import com.fleet.drone.Drone;
import com.fleet.drone.DroneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*
	@Bean
	CommandLineRunner runner(DroneRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			Drone drone = new Drone(
					"innokentii",
					"localhost:8081",
					""
			);

			repository.findByNickname("innokentii")
					.ifPresentOrElse(d -> {

					}, () -> {

					});
		};
	}
	*/
}
