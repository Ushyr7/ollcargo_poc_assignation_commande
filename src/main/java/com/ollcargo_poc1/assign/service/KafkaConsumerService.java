package com.ollcargo_poc1.assign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ollcargo_poc1.assign.model.Order;
import com.ollcargo_poc1.assign.repository.OrderRepository;

@Service
public class KafkaConsumerService {

    @Autowired
    private OrderRepository orderRepo;

    @KafkaListener(topics = "order", groupId = "ms-poc1-group")
    public void consume(Order order) {
        System.out.println("Consuming and saving the order : " + order.toString());
        orderRepo.save(order);
    }

}
