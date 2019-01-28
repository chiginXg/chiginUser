package com.admin.user.kafka;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MsgProducer {
     @Autowired
    private KafkaConfig kafkaConfig;
    private KafkaProducer  msgProducer;
    private ExecutorService executorService;

    private KafkaProducer createProducer(){
        Properties properties=new Properties();
        properties.put("bootStrap.servers",kafkaConfig.getBroker());
        properties.put("group.id",kafkaConfig.getProducerGroup());
        properties.put("auto.commit.interval.ms",kafkaConfig.getCommitInterval());
        properties.put("enable.auto.commit","true");
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        properties.put("key.desreializer","org.apache.kafka.common.serialization.StringDesreializer");
        properties.put("value.desreializer","org.apache.kafka.common.serialization.StringDesreializer");
        properties.put("partition.assigment.strategy","range");
        return new KafkaProducer<>(properties);
    }

    @PostConstruct
    public void init(){
        executorService = Executors.newFixedThreadPool(kafkaConfig.getProducerThreadNum());
        msgProducer =createProducer();
    }

    /**
     * 发送消息
     * @param message
     */
    public void service(String message){
        executorService.execute(()->{
            msgProducer.send(new ProducerRecord<>(kafkaConfig.getTopic(),null,message));
        });
    }

}


