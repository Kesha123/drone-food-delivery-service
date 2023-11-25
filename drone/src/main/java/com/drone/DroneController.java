package com.drone;


import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
public class DroneController {

    @Autowired
    private DroneService droneService;

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping("/drone/drone-register")
    public ResponseEntity<Drone> registerService (@RequestBody Drone data) {
        producerTemplate.sendBody("direct:registerRoute", data);
        return new ResponseEntity<>(droneService.getDrone(), HttpStatus.OK);
    }

    @RequestMapping(value = "/drone/drone-info", method = RequestMethod.GET)
    public ResponseEntity<Drone> infoService() {
        return new ResponseEntity<>(droneService.getDrone(), HttpStatus.OK);
    }

    @PostMapping("/drone/food-order")
    public ResponseEntity<String> foodOrderService (@RequestBody FoodOrder foodOrder) {
        droneService.getDrone().setAvailable(false);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
