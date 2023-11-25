package com.fleet.drone;

import com.fleet.drone.Drone;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DroneRepository extends MongoRepository<Drone, String> {
    Optional<Drone> findByNickname(String nickname);
    Optional<List<Drone>> findByAvailable(Boolean available);
}
