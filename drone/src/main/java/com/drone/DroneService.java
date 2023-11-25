package com.drone;

import org.springframework.stereotype.Service;

@Service
public class DroneService {

    private Drone drone;

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}
