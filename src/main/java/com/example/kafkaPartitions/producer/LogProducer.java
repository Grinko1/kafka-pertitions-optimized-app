package com.example.kafkaPartitions.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class LogProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public LogProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String key, String message) {
        kafkaTemplate.send("web-logs", key, message);
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("web-logs", message);
    }
}
