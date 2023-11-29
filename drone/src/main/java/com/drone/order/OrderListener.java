package com.drone.order;

import com.drone.drone.DroneService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

@Service
public class OrderListener {
    @Autowired
    Environment environment;

    @Autowired
    FoodOrderService foodOrderService;

    @Autowired
    DroneService droneService;

    @RabbitListener(queues = {"q.order-registration"})
    public void onUserRegistration(String event) {
        FoodOrder foodOrder = foodOrderService.convertJsonToObject(event);

        if (Objects.equals(droneService.getDrone().getId(), foodOrder.getDrone())) {
            droneService.setFoodOrder(foodOrder);
            droneService.setDroneAvailable(false);
        }
    }
}
