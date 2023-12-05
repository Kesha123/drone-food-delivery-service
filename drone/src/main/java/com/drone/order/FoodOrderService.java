package com.drone.order;

import com.drone.drone.DroneService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class FoodOrderService {

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    DroneService droneService;


    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private volatile boolean jobStarted = false;

    public FoodOrder convertJsonToObject(String serializedOrder) {
        try {
            return objectMapper.readValue(serializedOrder, FoodOrder.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to object", e);
        }
    }

    /*
    public void startDelivery() {
        if (!jobStarted) {
            scheduler.schedule(this::runTask, 5, TimeUnit.SECONDS);
            jobStarted = true;
        }
    }

    @Scheduled(fixedRate = 5000)
    private void runTask() {
        System.out.println("Order is delivered");
        droneService.setDroneAvailable(true);
        droneService.setFoodOrder(null);
        scheduler.shutdown();
    }

     */
}
