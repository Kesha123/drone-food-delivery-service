package com.fleet.drone;

import com.fleet.drone.DroneRepository;
import org.springframework.stereotype.Service;

@Service
public class DroneService {
    private final DroneRepository droneRepository;

    public DroneService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }
}
