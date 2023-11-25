package com.fleet.route;

import com.fleet.Drone;
import com.fleet.FleetService;
import com.fleet.FoodOrder;
import com.fleet.Order;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderRoute extends RouteBuilder {

    @Autowired
    private FleetService fleetService;

    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .log(LoggingLevel.ERROR, "HERE IS ERROR: ${exception}");

        from("direct:registerRoute")
                .routeId("food-order-create")
                .process(exchange -> {
                    Order order = exchange.getIn().getBody(Order.class);
                    Drone associatedDrone = order.getDrone();
                    String endpointUrl = String.format("http://%s/drone/food-order", associatedDrone.getHost());
                    exchange.getIn().setHeader(Exchange.HTTP_URI, endpointUrl);
                    exchange.getIn().setBody(order.getFoodOrder(), FoodOrder.class);
                })
                .marshal().json()
                .setHeader(Exchange.HTTP_METHOD, HttpMethods.POST)
                .setHeader("Content-Type", constant("application/json"))
                .to("http://dummy")
                .log("Order is assigned to the drone");
    }
}
