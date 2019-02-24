package com.admin.user.kafka;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MsgProducer {
     @Autowired
    private KafkaConfig kafkaConfig;
    private KafkaProducer  Producer;
    private ExecutorService executorService;

    private KafkaProducer createProducer(){
//        Semaphore sm = new Semaphore(90);
//
        Properties properties=new Properties();
//        properties.put("bootStrap.servers",kafkaConfig.getBroker());
        properties.put("bootstrap.servers","39.106.135.201:9092");
        properties.put("group.id","crawl");
        properties.put("auto.commit.interval.ms","2000");
        properties.put("enable.auto.commit","true");
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        properties.put("key.desreializer","org.apache.kafka.common.serialization.StringDesreializer");
        properties.put("value.desreializer","org.apache.kafka.common.serialization.StringDesreializer");
        return new KafkaProducer<>(properties);
    }


//    @PostConstruct
    public void init(){
        log.info("------------启动kafka生产者开始 2-----------");
        log.info("kafka配置为"+ JSON.toJSONString(kafkaConfig));
        executorService = Executors.newFixedThreadPool(8);
        Producer =createProducer();
    }

    /**
     * 发送消息
     * @param message
     */
    public void service(String message){
        executorService.execute(()->{
            Producer.send(new ProducerRecord<>("test1",null,message));
//            Producer.send(new ProducerRecord<>(kafkaConfig.getTopic(),null,message));
            System.out.println("生产者生产："+message);
        });
    }

//    public static void main(String[] args) {
//        MsgProducer producer = new MsgProducer();
//        producer.init();
//        producer.service("123456");
//    }
//    @Scheduled(cron = "/5 * * * * ?")
//    public void sendMessage(){
//        service("1");
//    }


//    public static void main(String[] args) {
//        String ss = "http://10.0.0.1:80/";
//        int i = ss.indexOf("/", 7);
//        System.out.println(i);
//    }
}


