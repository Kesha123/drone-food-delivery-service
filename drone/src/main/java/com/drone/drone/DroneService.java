package com.drone.drone;

import com.drone.drone.Drone;
import com.drone.order.FoodOrder;
import org.springframework.stereotype.Service;

@Service
public class DroneService {

    private Drone drone;

    private FoodOrder foodOrder;

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public FoodOrder getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(FoodOrder foodOrder) {
        this.foodOrder = foodOrder;
    }

    public void setDroneAvailable(boolean available) {
        this.drone.setAvailable(available);
    }
}
