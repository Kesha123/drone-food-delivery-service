package com.fleet;

import java.lang.reflect.Field;

public class Drone {

    private static int countDrone;
    private int identifier;
    private String nickname;
    private Double capacity;
    private int chargeLevel;

    private boolean available;

    private String host = "localhost:8080";

    public Drone(String nickname, Double capacity, String host) {
        this.identifier = countDrone;
        this.nickname = nickname;
        this.capacity = capacity;
        this.chargeLevel = 100;
        this.available = true;
        this.host = host;
        countDrone++;
    }


    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "{" +
                "identifier='" + identifier + '\'' +
                ", nickname='" + nickname + '\'' +
                ", capacity=" + capacity +
                ", chargeLevel=" + chargeLevel +
                ", available=" + available +
                ", host=" + host +
                '}';
    }


}
