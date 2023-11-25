package com.fleet;

public class Order {
    private FoodOrder foodOrder;
    private Drone drone;

    public Order(FoodOrder foodOrder, Drone drone) {
        this.foodOrder = foodOrder;
        this.drone = drone;
    }

    public FoodOrder getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(FoodOrder foodOrder) {
        this.foodOrder = foodOrder;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}
