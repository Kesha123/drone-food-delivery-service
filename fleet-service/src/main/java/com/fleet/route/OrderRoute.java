package com.fleet.route;


import com.fleet.drone.Drone;
import com.fleet.drone.DroneRepository;
import com.fleet.flight.FoodOrder;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderRoute extends RouteBuilder {

    @Autowired
    DroneRepository droneRepository;

    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .log(LoggingLevel.ERROR, "HERE IS ERROR: ${exception}");

        from("direct:registerRoute")
                .routeId("food-order-create")
                .process(exchange -> {
                    FoodOrder order = exchange.getIn().getBody(FoodOrder.class);
                    Optional<Drone> associatedDrone = droneRepository.findById(order.getDrone());
                    if (associatedDrone.isPresent()) {
                        String endpointUrl = String.format("http://%s/drone/food-order", associatedDrone.get().getHost());
                        exchange.getIn().setHeader(Exchange.HTTP_URI, endpointUrl);
                        exchange.getIn().setBody(order.getFoodOrder(), FoodOrder.class);
                    } else {

                    }
                })
                .marshal().json()
                .setHeader(Exchange.HTTP_METHOD, HttpMethods.POST)
                .setHeader("Content-Type", constant("application/json"))
                .to("http://dummy")
                .log("Order is assigned to the drone");
    }

}
