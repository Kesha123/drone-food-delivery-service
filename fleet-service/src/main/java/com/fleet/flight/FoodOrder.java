package com.fleet.flight;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Arrays;

@Document("orders")
public class FoodOrder {
    @Id
    private String id;
    private OrderStatus status;
    private String drone;
    private String restaurantLocation;
    private String customerLocation;

    private String[] foodOrder;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    public FoodOrder(String restaurantLocation, String customerLocation, String[] foodOrder) throws Exception {
        this.drone = null;
        this.restaurantLocation = restaurantLocation;
        this.customerLocation = customerLocation;
        this.foodOrder = foodOrder;
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

    public String[] getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(String[] foodOrder) {
        this.foodOrder = foodOrder;
    }

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
                "\"foodOrder\":\"" + Arrays.toString(foodOrder) + "\"," +
                "\"createdAt\":\"" + createdAt + "\"" +
                "}";
    }


}
