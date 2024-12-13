package com.example.kafkaPartitions.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;

public class LogConsumer implements AcknowledgingMessageListener<String, String> {
    private final Logger logger = LoggerFactory.getLogger(LogConsumer.class);

    @Override
    public void onMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        try {
            logger.info("Received message from partition: {}, with key: {}: {} ", record.partition(), record.key(), record.value());
            acknowledgment.acknowledge();
        }catch (Exception e){
            logger.error("Error processing message from partition: {}", record.partition(), e);
        }
    }

}

