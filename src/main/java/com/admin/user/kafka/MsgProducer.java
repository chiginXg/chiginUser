package com.admin.user.kafka;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

public class MsgProducer extends Thread{
    private String topic;
    public MsgProducer(String topic){
        super();
        this.topic = topic;
    }


    public void run() {
        Producer<Integer, String> producer = createProducer();
        int i=0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        while(true){
            producer.send(new KeyedMessage<Integer, String>(topic, "时间："+ df.format(new Date())+",message: " + i++));
            System.out.println("发送时间："+ df.format(new Date())+",message: " + i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private Producer<Integer, String> createProducer() {
        Properties properties = new Properties();
        properties.put("zookeeper.connect", "39.106.135.201:2181");//zookeeper安装在机器IP
        properties.put("serializer.class", StringEncoder.class.getName());
        properties.put("metadata.broker.list", "39.106.135.201:9092");// kafka安装的机器IP
        return new Producer<Integer, String>(new ProducerConfig(properties));
    }

    public static void main(String[] args) {
        new MsgProducer("test").start();
    }
}

