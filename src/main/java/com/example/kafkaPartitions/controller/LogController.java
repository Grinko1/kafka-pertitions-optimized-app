package com.example.kafkaPartitions.controller;


import com.example.kafkaPartitions.producer.LogProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    private final LogProducer producer;

    public LogController(LogProducer producer) {
        this.producer = producer;
    }

    @GetMapping
    public String makeLog() {
        sendMessagesWithUniqKey();
        sendMessageWithoutKey();
        sendMessageWithOneKey();
        return "Your actions sent to consumers";
    }

//   will send in different partitions on base unique key
    private void sendMessagesWithUniqKey() {
        for (int i = 0; i < 10; i++) {
            producer.sendMessage(String.valueOf(i), "new message with unique key " + i);
        }
    }
// will send in different partitions on base value
    private void sendMessageWithoutKey() {
        for (int i = 9; i < 20; i++) {
            producer.sendMessage("new message " + i);
        }
    }
//   will send in one partition cause equal key
    private void sendMessageWithOneKey(){
        for (int i = 19; i < 30; i++) {
            producer.sendMessage(String.valueOf(200),"new message with key 200");
        }
    }
}
