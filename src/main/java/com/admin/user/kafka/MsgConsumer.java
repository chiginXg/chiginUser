package com.admin.user.kafka;

import com.alibaba.fastjson.JSON;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.consumer.Consumer;
import kafka.message.Message;
import kafka.message.MessageAndMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MsgConsumer{

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private KafkaConfig kafkaConfig;

//    @Bean
//    @ConfigurationProperties(prefix = "kafka")
//    private KafkaConfig getKafka(){
//        return new KafkaConfig();
//    }

    private ConsumerConnector createConsumer(Properties properties){
        return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
    }

    public MsgConsumer(){
        Properties pro= new Properties();
        kafkaConfig = new KafkaConfig();
        System.out.println(JSON.toJSONString(kafkaConfig));
        KafkaConfig.ConsumerTopic consumerTopic = kafkaConfig.getConsumerTopic().get("messagePoll");
//        pro.put("zookeeper.connect",kafkaConfig.getZookeeperConnect());
        pro.put("zookeeper.connect","39.106.135.201:2181");
        pro.put("group.id","user");
        pro.put("auto.commit.interval.ms","2000");
        pro.put("auto.commit.enable","true");
//        ThreadFactory threadFactory = new ThreadFactoryBuilder();
        Executor threadPool = Executors.newFixedThreadPool(2);
        ConsumerConnector consumer = createConsumer(pro);
        Map<String,Integer> map= new HashMap<>();
        map.put("test1",2);
//        map.put(consumerTopic.getName(),consumerTopic.getConsumerNumber());
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = consumer.createMessageStreams(map);
        List<KafkaStream<byte[], byte[]>> streams = messageStreams.get("test1");
        for (int i = 0; i < 2 ; i++) {
            final  KafkaStream<byte[], byte[]> kafkaStream = streams.get(i);
            threadPool.execute(()->{
                startConsumer(kafkaStream,consumerTopic);
            });
        }

//        //7、消费消息任务
//        for (final KafkaStream<Message> stream : streams) {
//        //stream 是MessageAndMetadata类型，包含topic、message字段
//            //8、 为每一个partition分配一个线程去消费
//            executor.submit(new Runnable() {
//                public void run() {
//                    for (MessageAndMetadata msgAndMetadata : stream) {
//                        // process message (msgAndMetadata.message())
//                        System.out.println("topic: " + msgAndMetadata.topic());
//                        Message message = (Message) msgAndMetadata.message();
//                        ByteBuffer buffer = message.payload();
//                        byte[] bytes = new byte[message.payloadSize()];
//                        buffer.get(bytes);
//                        String tmp = new String(bytes);
//                        System.out.println("message content: " + tmp);
//                    }
//                }
//            });
//        }


    }

    private void startConsumer(final KafkaStream<byte[], byte[]> kafkaStreams,
                               KafkaConfig.ConsumerTopic consumerTopicMap){
            ExecutorService workPool = Executors.newFixedThreadPool(5);
            for (MessageAndMetadata<byte[], byte[]> kafkaStream:kafkaStreams
            ) {
                byte[] message = kafkaStream.message();
                if(message ==null){
                    continue;
                }
                final String data = new String(message);
                System.out.println("---"+data);
                List<String> messages = new ArrayList<>();
                messages.add(data);
                workPool.execute(()->{
                    consumerService.pollMessage(messages);
                });

            }
    }

}