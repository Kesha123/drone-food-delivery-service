package com.drone.drone;

public class Drone {

    private String id;

    private String nickname;
    private int chargeLevel;

    private boolean available;

    private String host;
    private String location;
    private String order;

    public Drone(String nickname, String host, String location) {
        this.nickname = nickname;
        this.chargeLevel = 100;
        this.available = true;
        this.host = host;
        this.location = location;
        this.order = null;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }


    @Override
    public String toString() {
        return "Drone{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", chargeLevel=" + chargeLevel +
                ", available=" + available +
                ", host='" + host + '\'' +
                ", location='" + location + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
