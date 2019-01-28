package com.admin.user.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "kafka")
public class KafkaConfig {
    private String broker;
    private String producerGroup;
    private String commitInterval;
    private String topic;
    private String zookeeperConnect;
    private int producerThreadNum;
    private String autoCommit;
    private Map<String,ConsumerTopic> ConsumerTopic = new HashMap<>();

    @Data
    public static class ConsumerTopic{
        private String name;
        private String group;
        private String handler;
        private int consumerNumber;
        private int workThreadNumber;
        private int pollNum;
    }
}
