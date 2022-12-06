package com.ollcargo_poc1.assign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ollcargo_poc1.assign.model.Assignation;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, Assignation> kafkaTemplate;

    private String kafkaTopic = "assignation";

    public void send(Assignation assignation) {
        kafkaTemplate.send(kafkaTopic, assignation);
    }
}