package com.fleet;

import com.fleet.drone.Drone;
import com.fleet.drone.DroneRepository;
import com.fleet.flight.FoodOrderRepository;
import com.fleet.flight.FoodOrder;
import com.fleet.flight.FoodOrderService;
import com.fleet.flight.OrderStatus;
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

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("dronora")
public class FlightController {

    @Autowired
    FoodOrderRepository foodOrderRepository;

    @Autowired
    private FoodOrderService foodOrderService;

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping("/flights/all")
    public ResponseEntity<List<FoodOrder>> allOrders() {
        try {
            List<FoodOrder> orders = foodOrderRepository.findAll();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/flights")
    public ResponseEntity<FoodOrder> createFoodOrder(@RequestBody FoodOrder data) {
        try {
            data.setStatus(OrderStatus.CREATED);
            FoodOrder foodOrder = foodOrderRepository.save(data);
            foodOrderService.createOrder(foodOrder);
            return new ResponseEntity<>(foodOrder, HttpStatus.CREATED);
        } catch ( Exception e) {
            e.printStackTrace();
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
