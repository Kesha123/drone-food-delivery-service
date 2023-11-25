package com.fleet.drone;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Field;

@Document
public class Drone {

    @Id
    private String id;
    @Indexed(unique = true)
    private String nickname;
    private Double capacity;
    private int chargeLevel;

    private boolean available;

    private String host;

    public Drone(String nickname, Double capacity, String host) {
        this.nickname = nickname;
        this.capacity = capacity;
        this.chargeLevel = 100;
        this.available = true;
        this.host = host;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", capacity=" + capacity +
                ", chargeLevel=" + chargeLevel +
                ", available=" + available +
                ", host=" + host +
                '}';
    }



}
