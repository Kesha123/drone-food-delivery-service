package com.drone;

import java.util.Arrays;

public class FoodOrder {
    private Double price;

    private String[] dishes;
    private String restaurantAddress;
    private String customerAddress;
    private Double capacityRequired;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Double getCapacityRequired() {
        return capacityRequired;
    }

    public void setCapacityRequired(Double capacityRequired) {
        this.capacityRequired = capacityRequired;
    }

    public String[] getDishes() {
        return dishes;
    }

    public void setDishes(String[] dishes) {
        this.dishes = dishes;
    }


    @Override
    public String toString() {
        return "FoodOrder{" +
                "price=" + price +
                ", dishes=" + Arrays.toString(dishes) +
                ", restaurantAddress='" + restaurantAddress + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", capacityRequired=" + capacityRequired +
                '}';
    }
}
