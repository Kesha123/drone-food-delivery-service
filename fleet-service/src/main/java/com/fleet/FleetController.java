package com.fleet;

import com.fleet.responses.DroneInteraction;
import com.fleet.responses.StatusCode;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FleetController {

    @Autowired
    private FleetService fleetService;

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping("/fleet/drone-register")
    public ResponseEntity<Drone> droneRegisterService (@RequestBody Drone data) throws NoSuchFieldException, IllegalAccessException {
        data.setIdentifier(fleetService.getFleet().values().size());
        fleetService.getFleet().put(data.getIdentifier(), data);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/fleet/drone-delete/{id}")
    public ResponseEntity<DroneInteraction> droneDeleteService (@PathVariable int id) {
        fleetService.getFleet().remove(id);
        return new ResponseEntity<>( new DroneInteraction(id, "Drone has been deleted", StatusCode.DRONE_DELETE) ,HttpStatus.OK);
    }

    @PostMapping("/fleet/food-order")
    public ResponseEntity<Drone> foodOrderService (@RequestBody FoodOrder foodOrder) {
        Drone availableDrone = null;
        for (Drone drone : fleetService.getFleet().values()) {
            if (drone.getCapacity() >= foodOrder.getCapacityRequired() & drone.isAvailable()) {
                availableDrone = drone;
                break;
            }
        }
        Order order = new Order(foodOrder, availableDrone);
        producerTemplate.sendBody("direct:registerRoute", order);
        return new ResponseEntity<>(availableDrone, HttpStatus.OK);
    }

    @PutMapping("/fleet/drone-status-update")
    public ResponseEntity<DroneInteraction> statusUpdateService (@RequestBody Drone droneStatusUpdate) {
        Drone drone = fleetService.getFleet().get(droneStatusUpdate.getIdentifier());
        drone.setCapacity(droneStatusUpdate.getCapacity());
        drone.setChargeLevel(droneStatusUpdate.getChargeLevel());
        drone.setAvailable(droneStatusUpdate.isAvailable());
        return new ResponseEntity<>(new DroneInteraction(droneStatusUpdate.getIdentifier(), "Drone has been updated", StatusCode.DRONE_STATUS_UPDATE), HttpStatus.OK);
    }

    @RequestMapping(value = "/fleet/drone-info", method = RequestMethod.GET)
    public ResponseEntity<Map<Integer, Drone>> infoService() {
        return new ResponseEntity<>(fleetService.getFleet(), HttpStatus.OK);
    }

    @RequestMapping(value = "/fleet/drone-info/{id}", method = RequestMethod.GET)
    public ResponseEntity<Drone> infoServiceIndividual(@PathVariable int id) {
        return new ResponseEntity<>(fleetService.getFleet().get(id), HttpStatus.OK);
    }
}
