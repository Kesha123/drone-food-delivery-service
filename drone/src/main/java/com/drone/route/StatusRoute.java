package com.drone.route;

import com.drone.drone.Drone;
import com.drone.drone.DroneService;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatusRoute extends RouteBuilder {

    @Autowired
    private DroneService droneService;

    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .log(LoggingLevel.ERROR, "HERE IS ERROR: ${exception}");

        from("scheduler:RestToQueue?delay=1000")
                .routeId("status-update")
                .setHeader(Exchange.HTTP_METHOD, HttpMethods.PUT)
                .setHeader("Content-Type", constant("application/json"))
                .bean(droneService, "getDrone")
                .choice()
                .when(simple("${body.id} != null"))
                .process(exchange -> {
                    Drone drone = exchange.getIn().getBody(Drone.class);
                    String endpointUrl = String.format("http://localhost:8080/dronora/drones/%s", drone.getId());
                    exchange.getIn().setHeader(Exchange.HTTP_URI, endpointUrl);
                })
                .marshal().json()
                .setBody().simple("${body}")
                .to("http://dummy")
                .otherwise()
                .endChoice();
    }
}
