package com.admin.user.kafka;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.consumer.Consumer;
import kafka.message.MessageAndMetadata;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MsgConsumer{

    @Autowired
    private ConsumerService consumerService;

    private ConsumerConnector createConsumer(Properties properties){
        return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
    }

    public MsgConsumer(){
        Properties pro= new Properties();
        KafkaConfig kafkaConfig= new KafkaConfig();
        KafkaConfig.ConsumerTopic consumerTopic = kafkaConfig.getConsumerTopic().get("");
        pro.put("zookeeper.connect",kafkaConfig.getZookeeperConnect());
        pro.put("group.id",consumerTopic.getGroup());
        pro.put("auto.commit.interval.ms",kafkaConfig.getCommitInterval());
        pro.put("auto.commit.enable",kafkaConfig.getAutoCommit());
//        ThreadFactory threadFactory = new ThreadFactoryBuilder();
        Executor threadPool = Executors.newFixedThreadPool(consumerTopic.getWorkThreadNumber());
        ConsumerConnector consumer = createConsumer(pro);
        Map<String,Integer> map= new HashMap<>();
        map.put(consumerTopic.getName(),consumerTopic.getConsumerNumber());
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = consumer.createMessageStreams(new HashMap<String,Integer>());
        List<KafkaStream<byte[], byte[]>> kafkaStreams1 = messageStreams.get(consumerTopic.getName());
        for (int i = 0; i <consumerTopic.getConsumerNumber() ; i++) {
           final KafkaStream<byte[], byte[]> kafkaStreams = kafkaStreams1.get(i);
            threadPool.execute(()->{
                startConsumer(kafkaStreams,consumerTopic);
            });
        }

    }

    private void startConsumer(final KafkaStream<byte[], byte[]> kafkaStreams,
                               KafkaConfig.ConsumerTopic consumerTopicMap){
            ExecutorService workPool = Executors.newFixedThreadPool(consumerTopicMap.getWorkThreadNumber());
            for (MessageAndMetadata<byte[], byte[]> kafkaStream:kafkaStreams
            ) {
                byte[] message = kafkaStream.message();
                if(message ==null){
                    continue;
                }
                final String data = new String(message);
                List<String> messages = new ArrayList<>();
                messages.add(data);
                workPool.execute(()->{
                    consumerService.pollMessage(messages);
                });

            }
    }

}