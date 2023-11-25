package com.fleet.drone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DroneService {

    @Autowired
    DroneRepository droneRepository;

    public Drone getDrone(String id) {
        Optional<Drone> droneData = droneRepository.findById(id);
        if (droneData.isPresent()) {
            return droneData.get();
        } else {
            return null;
        }
    }
}
