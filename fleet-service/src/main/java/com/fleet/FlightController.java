package com.fleet;

import com.fleet.drone.Drone;
import com.fleet.drone.DroneRepository;
import com.fleet.flight.FoodOrderRepository;
import com.fleet.flight.FoodOrder;
import com.fleet.flight.FoodOrderService;
import org.apache.camel.ProducerTemplate;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;

import java.util.Optional;

@RestController
@RequestMapping("dronora")
public class FlightController {

    @Autowired
    FoodOrderRepository foodOrderRepository;

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private FoodOrderService foodOrderService;

    @PostMapping("/flights")
    public ResponseEntity<FoodOrder> createFoodOrder(@RequestBody FoodOrder data) {
        try {
            FoodOrder foodOrder = foodOrderRepository.save(data);
            // producerTemplate.sendBody("direct:registerRoute", foodOrder);
            foodOrderService.createOrder(foodOrder);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch ( Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/flights/{foodOrderId}")
    public ResponseEntity<FoodOrder> getFoodOrder(@PathVariable String foodOrderId) {
        Optional<FoodOrder> foodOrder = foodOrderRepository.findById(foodOrderId);
        if (foodOrder.isPresent()) {
            return new ResponseEntity<>(foodOrder.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/flights/{foodOrderId}")
    public ResponseEntity<FoodOrder> updateFoodOrder(@PathVariable String foodOrderId, @RequestBody FoodOrder data) {
        Optional<FoodOrder> foodOrderData = foodOrderRepository.findById(foodOrderId);
        if (foodOrderData.isPresent()) {
            FoodOrder foodOrder = foodOrderData.get();
            foodOrder.setStatus(data.getStatus());
            return new ResponseEntity<>(foodOrderRepository.save(foodOrder), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/flights/{foodOrderId}")
    public  ResponseEntity<Void> deleteFoodOrder(@PathVariable String foodOrderId) {
        try {
            foodOrderRepository.deleteById(foodOrderId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
