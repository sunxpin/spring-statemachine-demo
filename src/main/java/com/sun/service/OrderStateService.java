package com.sun.service;

import com.sun.dao.OrderRepo;
import com.sun.entity.Order;
import com.sun.events.OrderStatusChangeEvent;
import com.sun.handler.PersistStateMachineHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringJoiner;

/**
 * @Date 2020/5/10 22:40
 */
@Service
public class OrderStateService {

    private PersistStateMachineHandler handler;


    public OrderStateService(PersistStateMachineHandler handler) {
        this.handler = handler;
    }

    @Autowired
    private OrderRepo repo;


    public String listDbEntries() {
        List<Order> orders = repo.findAll();
        StringJoiner sj = new StringJoiner(",");
        for (Order order : orders) {
            sj.add(order.toString());
        }
        return sj.toString();
    }


    public boolean change(int order, OrderStatusChangeEvent event) {
        Order o = repo.findByOrderId(order);
        return handler.handleEventWithState(
                MessageBuilder
                        .withPayload(event).
                        setHeader("order", order).build(),
                o.getStatus());
    }
}
