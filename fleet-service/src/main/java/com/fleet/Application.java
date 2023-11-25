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

	@Bean
	CommandLineRunner runner(DroneRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			Drone drone = new Drone(
					"innokentii",
					1.0,
					"localhost:8081"
			);

			/*
			Query query = new Query();
			query.addCriteria(Criteria.where("nickname").is("innokentii"));
			List<Drone> drones = mongoTemplate.find(query, Drone.class);
			if (drones.size() > 1) throw new IllegalStateException();
			if (drones.isEmpty()) {
				repository.save(drone);
			}

			 */

			repository.findByNickname("innokentii")
					.ifPresentOrElse(d -> {

					}, () -> {

					});
		};
	}
}
