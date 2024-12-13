package com.example.kafkaPartitions.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Value("${kafka.topic.partitions:3}")
    private int partitions;
    @Value("${kafka.url.servers}")
    private String url;
    @Value(("${kafka.topic.replication-factor:3}"))
    private Short replicationFactor;


    @Bean
    public NewTopic webLogsTopic() {
        Map<String, String> configs = new HashMap<>();
        configs.put("retention.ms", "604800000"); // save 7 days
        return new NewTopic("web-logs", partitions, replicationFactor).configs(configs);
    }
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, url);
        return new KafkaAdmin(configs);
    }

}
