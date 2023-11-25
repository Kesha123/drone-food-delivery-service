package com.fleet.responses;

public class DroneRegister {
    private int identifier;

    public DroneRegister(int identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "DroneRegister{" +
                "identifier=" + identifier +
                '}';
    }

    public int getId() {
        return identifier;
    }

    public void setId(int identifier) {
        this.identifier = identifier;
    }
}
