package com.fleet;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FleetService {

    private Map<Integer, Drone> fleet = new HashMap<>();

    public Map<Integer, Drone> getFleet() {
        return fleet;
    }

    public void setFleet(Map<Integer, Drone> fleet) {
        this.fleet = fleet;
    }

}
