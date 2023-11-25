package com.drone.route;

import com.drone.Drone;
import com.drone.DroneService;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DroneRoute extends RouteBuilder {

    @Autowired
    private DroneService droneService;

    @Override
    public void configure() {
        onException(Exception.class)
                .log(LoggingLevel.ERROR, "HERE IS ERROR: ${exception}");

        from("direct:registerRoute")
                .marshal().json()
                .setHeader("Content-Type", constant("application/json"))
                .to("http://localhost:8081/fleet/drone-register")
                .log("Data forwarded to Fleet")
                .unmarshal(new JacksonDataFormat(Drone.class))
                .process(exchange -> {
                    Drone drone = exchange.getIn().getBody(Drone.class);
                    droneService.setDrone(drone);
                });
    }
}
