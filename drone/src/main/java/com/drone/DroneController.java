package com.drone;

import com.drone.drone.Drone;
import com.drone.drone.DroneService;
import com.drone.order.FoodOrder;
import com.drone.order.FoodOrderService;
import com.drone.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DroneController {
    @Autowired
    DroneService droneService;

    @Autowired
    FoodOrderService foodOrderService;

    @PostMapping("/drone/register")
    public ResponseEntity<Drone> createDrone(@RequestBody Drone data) {
        try {
            droneService.setDrone(data);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch ( Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/drone/portal")
    public String index() {
        if (droneService.getFoodOrder() != null) return "index";
        else return "delivery";
    }

    @PostMapping("/drone/start-delivery")
    public String startDelivery() {
        try {
            FoodOrder order = droneService.getFoodOrder();
            order.setStatus(OrderStatus.DELIVERING);
            droneService.setFoodOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "delivery";
    }

}
