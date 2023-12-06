package com.fleet;

import com.fleet.drone.Drone;
import com.fleet.drone.DroneRepository;
import com.fleet.flight.FoodOrder;
import com.fleet.flight.FoodOrderRepository;
import com.fleet.flight.FoodOrderService;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("dronora")
public class DroneController {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    FoodOrderRepository foodOrderRepository;

    @Autowired
    FoodOrderService foodOrderService;


    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping("/drones/all")
    public ResponseEntity<List<Drone>> allDrones() {
        try {
            List<Drone> drones = droneRepository.findAll();
            return new ResponseEntity<>(drones, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/drones")
    public ResponseEntity<Drone> createDrone(@RequestBody Drone data) {
        try {
            Drone drone = droneRepository.save(data);
            producerTemplate.sendBody("direct:registerRoute", drone);
            return new ResponseEntity<>(drone, HttpStatus.CREATED);
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
            drone.setNickname(data.getNickname() != null ? data.getNickname() : drone.getNickname());
            drone.setHost(data.getHost() != null ? data.getHost() : drone.getHost());
            drone.setChargeLevel(data.getChargeLevel());
            drone.setLocation(data.getLocation());
            drone.setAvailable(data.isAvailable());
            droneRepository.save(drone);
            return new ResponseEntity<>(drone, HttpStatus.OK);
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
