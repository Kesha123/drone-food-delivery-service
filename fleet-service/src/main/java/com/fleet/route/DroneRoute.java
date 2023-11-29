package com.fleet.route;

import com.fleet.drone.Drone;
import com.fleet.flight.FoodOrder;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DroneRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .log(LoggingLevel.ERROR, "HERE IS ERROR: ${exception}");

        from("direct:registerRoute")
                .process(exchange -> {
                    Drone drone = exchange.getIn().getBody(Drone.class);
                    String endpointUrl = String.format("http://%s/drone/register", drone.getHost());
                    exchange.getIn().setHeader(Exchange.HTTP_URI, endpointUrl);
                    exchange.getIn().setBody(drone, FoodOrder.class);
                })
                .marshal().json()
                .setHeader(Exchange.HTTP_METHOD, HttpMethods.POST)
                .setHeader("Content-Type", constant("application/json"))
                .to("http://dummy");

    }
}
