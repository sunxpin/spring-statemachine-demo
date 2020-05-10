package com.sun;

import com.sun.events.OrderStatusChangeEvent;
import com.sun.handler.PersistStateMachineHandler;
import com.sun.listener.OrderPersistStateChangeListener;
import com.sun.service.OrderStateService;
import com.sun.states.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateMachine;

@SuppressWarnings("ALL")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class StatemachineApplication implements CommandLineRunner {

//    @Autowired
//    private StateMachine<States, Events> stateMachine;

    @Autowired
    private StateMachine<OrderStatus, OrderStatusChangeEvent> orderStateMachine;


    @Bean
    public OrderStateService persist() {
        PersistStateMachineHandler handler = persistStateMachineHandler();
        handler.addPersistStateChangeListener(persistStateChangeListener());
        return new OrderStateService(handler);
    }

    @Bean
    public PersistStateMachineHandler persistStateMachineHandler() {
        return new PersistStateMachineHandler(orderStateMachine);
    }

    @Bean
    public OrderPersistStateChangeListener persistStateChangeListener() {
        return new OrderPersistStateChangeListener();
    }


    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(StatemachineApplication.class, args);
        Thread.currentThread().join();
    }

    @Override
    public void run(String... args) throws Exception {
//        stateMachine.sendEvent(Events.E1);
//        stateMachine.sendEvent(Events.E2);
    }
}
