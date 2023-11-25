package com.fleet.requests;

public class DroneStatusUpdate {
    private Double capacity;
    private int chargeLevel;

    public DroneStatusUpdate(Double capacity, int chargeLevel) {
        this.capacity = capacity;
        this.chargeLevel = chargeLevel;
    }

    @Override
    public String toString() {
        return "DroneStatusUpdate{" +
                "capacity=" + capacity +
                ", chargeLevel=" + chargeLevel +
                '}';
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public int getChargeLevel() {
        return chargeLevel;
    }

    public void setChargeLevel(int chargeLevel) {
        this.chargeLevel = chargeLevel;
    }
}
