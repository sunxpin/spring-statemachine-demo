package com.sun.listener;

import com.sun.dao.OrderRepo;
import com.sun.entity.Order;
import com.sun.events.OrderStatusChangeEvent;
import com.sun.handler.PersistStateMachineHandler;
import com.sun.states.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

public class OrderPersistStateChangeListener implements PersistStateMachineHandler.PersistStateChangeListener {


    @Autowired
    private OrderRepo repo;


    @Override
    public void onPersist(
            State<OrderStatus, OrderStatusChangeEvent> state,
            Message<OrderStatusChangeEvent> message,
            Transition<OrderStatus, OrderStatusChangeEvent> transition,
            StateMachine<OrderStatus, OrderStatusChangeEvent> stateMachine) {

        if (message != null && message.getHeaders().containsKey("order")) {
            Integer order = message.getHeaders().get("order", Integer.class);
            Order o = repo.findByOrderId(order);
            OrderStatus status = state.getId();
            o.setStatus(status);
            repo.save(o);

        }
    }
}