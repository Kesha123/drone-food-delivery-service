package com.fleet.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.EnumSet;

@Document
public class FoodOrder {
    @Id
    private String id;
    private OrderStatus status;
    private String drone;
    private String restaurantLocation;
    private String customerLocation;

    private String foodOrder;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    @Version
    private Integer version;

    @Autowired
    private StateMachine<OrderStatus, OrderStatus> stateMachine;

    public FoodOrder(String restaurantLocation, String customerLocation, String foodOrder) throws Exception {
        this.drone = null;
        this.restaurantLocation = restaurantLocation;
        this.customerLocation = customerLocation;
        this.foodOrder = foodOrder;

        stateMachine = StateMachineBuilder.builder()
                .configureStates()
                .withStates()
                .initial(OrderStatus.WAITING)
                .states(Collections.singleton(EnumSet.allOf(OrderStatus.class).clone()))
                .and()
                .getClass()
                .withExternal()
                .source(OrderStatus.WAITING).target(OrderStatus.CREATED)
                .and()
                .withExternal()
                .source(OrderStatus.CREATED).target(OrderStatus.COOKING)
                .and()
                .withExternal()
                .source(OrderStatus.COOKING).target(OrderStatus.DELIVERING)
                .and()
                .withExternal()
                .source(OrderStatus.DELIVERING).target(OrderStatus.DELIVERED)
                .and()
                .build();

        // Set the initial state
        stateMachine.start();
        status = stateMachine.getState().getId();
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
        stateMachine.sendEvent(status);
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

    public String getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(String foodOrder) {
        this.foodOrder = foodOrder;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"status\":\"" + status + '\"' +
                ", \"drone\":\"" + drone + '\"' +
                ", \"restaurantLocation\":\"" + restaurantLocation + '\"' +
                ", \"customerLocation\":\"" + customerLocation + '\"' +
                '}';
    }


}
