package com.fleet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleet.drone.Drone;
import com.fleet.drone.DroneRepository;
import com.fleet.flight.FoodOrder;
import com.fleet.flight.FoodOrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodOrderConsumer {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    FoodOrderRepository foodOrderRepository;

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "orders")
    public void receiveOrder(String message) throws JsonProcessingException {
        Optional<List<Drone>> droneData = droneRepository.findByAvailable(true);

        if (droneData.isPresent() && !droneData.get().isEmpty()) {
            Drone drone = droneData.get().get(0);
            FoodOrder foodOrder = this.convertJsonToObject(message);
            foodOrder.setDrone(drone.getId());
            drone.setAvailable(false);
            foodOrderRepository.save(foodOrder);
            droneRepository.save(drone);
        }
    }

    private FoodOrder convertJsonToObject(String serializedOrder) {
        try {
            return objectMapper.readValue(serializedOrder, FoodOrder.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to object", e);
        }
    }
}
