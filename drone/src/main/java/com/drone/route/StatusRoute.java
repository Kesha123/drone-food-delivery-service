package com.drone.route;

import com.drone.DroneService;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
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

        from("scheduler:RestToQueue?delay=10000")
                .routeId("status-update")
                .setHeader(Exchange.HTTP_METHOD, HttpMethods.PUT)
                .setHeader("Content-Type", constant("application/json"))
                .bean(droneService, "getDrone")
                .marshal().json()
                .setBody().simple("${body}")
                .to("http://localhost:8081/fleet/drone-status-update")
                .log("Status has been updated");
    }
}
