package com.drone;

import java.util.UUID;

public class Drone {

    private int identifier;
    private String nickname;
    private Double capacity;
    private int chargeLevel = 100;

    private boolean available = true;

    private String host = "localhost:8080";

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "identifier='" + identifier + '\'' +
                ", nickname='" + nickname + '\'' +
                ", capacity=" + capacity +
                ", chargeLevel=" + chargeLevel +
                ", available=" + available +
                ", host=" + host +
                '}';
    }


}
