package com.fleet.flight;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleet.drone.Drone;
import com.fleet.drone.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodOrderService {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    FoodOrderRepository foodOrderRepository;

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    public void createOrder(FoodOrder order) {
        Sort sort = Sort.by(Sort.Direction.DESC, "chargeLevel");
        Optional<List<Drone>> droneData = droneRepository.findByAvailable(true, sort);
        if (droneData.isPresent()) {
            Drone drone = droneData.get().get(0);
            this.configureOrderInDatabase(drone, order);
        } else {
            order.setStatus(OrderStatus.WAITING);
            foodOrderRepository.save(order);
        }
    }

    public void createOrder(Drone drone) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createdAt");
        Optional<List<FoodOrder>> ordersData = foodOrderRepository.findByStatus(OrderStatus.WAITING, sort);
        if (!ordersData.isPresent()) return;
        FoodOrder order = ordersData.get().get(0);
        this.configureOrderInDatabase(drone, order);
    }

    private FoodOrder convertJsonToObject(String serializedOrder) {
        try {
            return objectMapper.readValue(serializedOrder, FoodOrder.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to object", e);
        }
    }

    private void configureOrderInDatabase(Drone drone, FoodOrder order) {
        order.setDrone(drone.getId());
        order.setStatus(OrderStatus.CREATED);
        drone.setOrder(order.getId());
        drone.setAvailable(false);
        foodOrderRepository.save(order);
        droneRepository.save(drone);
    }
}
