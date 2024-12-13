package com.example.kafkaPartitions.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class CustomPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        int partitionCount = cluster.partitionCountForTopic(topic);

        if (key != null) {
            // use key hash to select partition
            return Math.abs(key.hashCode()) % partitionCount;
        } else {
            // use value hash to select partition
            String message = (String) value;
            return Math.abs(message.hashCode()) % partitionCount;
        }
    }

    @Override
    public void close() {}

    @Override
    public void configure(Map<String, ?> configs) {}
}
