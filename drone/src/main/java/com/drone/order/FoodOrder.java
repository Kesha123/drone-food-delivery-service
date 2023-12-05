package com.drone.order;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class FoodOrder {

    private String id;
    private OrderStatus status;
    private String drone;
    private String restaurantLocation;
    private String customerLocation;

    //private List<String> foodOrder;

    private LocalDateTime createdAt;


    public FoodOrder(String restaurantLocation, String customerLocation) throws Exception {
        this.drone = null;
        this.restaurantLocation = restaurantLocation;
        this.customerLocation = customerLocation;
        // this.foodOrder = foodOrder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public String getDrone() {
        return drone;
    }

    public void setDrone(String drone) {
        this.drone = drone;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }

    public void setRestaurantLocation(String restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }

    public String getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(String customerLocation) {
        this.customerLocation = customerLocation;
    }

    /*
    public List<String> getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(List<String> foodOrder) {
        this.foodOrder = foodOrder;
    }
    */

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + "\"," +
                "\"status\":\"" + status + "\"," +
                "\"drone\":\"" + drone + "\"," +
                "\"restaurantLocation\":\"" + restaurantLocation + "\"," +
                "\"customerLocation\":\"" + customerLocation + "\"," +
                // "\"foodOrder\":\"" + foodOrder + "\"," +
                "\"createdAt\":\"" + createdAt + "\"" +
                "}";
    }

}