package com.fleet.route;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FoodOrderConsumer {

    @RabbitListener(queues = "orders")
    public void receiveMessage(String message) {
        System.out.println(message);
    }
}
