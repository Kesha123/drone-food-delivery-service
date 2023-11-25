package com.fleet;

import com.fleet.drone.Drone;
import com.fleet.drone.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("dronora")
public class DroneController {

    @Autowired
    DroneRepository droneRepository;

    @PostMapping("/drones")
    public ResponseEntity<Drone> createDrone(@RequestBody Drone data) {
        try {
            Drone drone = droneRepository.save(data);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch ( Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/drones/{droneId}")
    public ResponseEntity<Drone> getDrone(@PathVariable String droneId) {
        Optional<Drone> drone = droneRepository.findById(droneId);
        if (drone.isPresent()) {
            return new ResponseEntity<>(drone.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/drones/{droneId}")
    public ResponseEntity<Drone> updateDrone(@PathVariable String droneId, @RequestBody Drone data) {
        Optional<Drone> droneData = droneRepository.findById(droneId);
        if (droneData.isPresent()) {
            Drone drone = droneData.get();
            drone.setNickname(data.getNickname());
            drone.setHost(data.getHost());
            drone.setChargeLevel(data.getChargeLevel());
            drone.setLocation(data.getLocation());
            return new ResponseEntity<>(droneRepository.save(drone), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/drones/{droneId}")
    public  ResponseEntity<Void> deleteDrone(@PathVariable String droneId) {
        try {
            droneRepository.deleteById(droneId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
