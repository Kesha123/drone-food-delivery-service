package com.drone;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final CachingConnectionFactory cachingConnectionFactory;

    public RabbitMQConfig(CachingConnectionFactory cachingConnectionFactory) {
        this.cachingConnectionFactory = cachingConnectionFactory;
    }

    @Bean
    public Queue createUserRegistrationQueue() {
        return new Queue("q.order-registration");
    }


}
