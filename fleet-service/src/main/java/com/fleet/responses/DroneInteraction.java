package com.fleet.responses;

public class DroneInteraction {
    private int id;
    private String message;
    private StatusCode statusCode;

    public DroneInteraction(int id, String message, StatusCode statusCode) {
        this.id = id;
        this.message = message;
        this.statusCode = statusCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "DroneInteraction{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
