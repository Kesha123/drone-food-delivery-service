package com.fleet.flight;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FoodOrder {
    @Id
    private String id;
    private OrderStatus status;
    private String drone;
    private String restaurantLocation;
    private String customerLocation;

    public FoodOrder(OrderStatus status, String drone, String restaurantLocation, String customerLocation) {
        this.status = status;
        this.drone = drone;
        this.restaurantLocation = restaurantLocation;
        this.customerLocation = customerLocation;
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

    @Override
    public String toString() {
        return "FoodOrder{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", drone='" + drone + '\'' +
                ", restaurantLocation='" + restaurantLocation + '\'' +
                ", customerLocation='" + customerLocation + '\'' +
                '}';
    }
}
