package com.fleet.flight;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderStatus> {

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStatus> transitions) throws Exception {
        transitions
                .withExternal()
                .source(OrderStatus.WAITING).target(OrderStatus.CREATED)
                .and()
                .withExternal()
                .source(OrderStatus.CREATED).target(OrderStatus.COOKING)
                .and()
                .withExternal()
                .source(OrderStatus.COOKING).target(OrderStatus.DELIVERING)
                .and()
                .withExternal()
                .source(OrderStatus.DELIVERING).target(OrderStatus.DELIVERED);
    }
}

