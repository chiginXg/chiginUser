package com.admin.user.kafka;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import kafka.consumer.Consumer;
//import kafka.consumer.ConsumerConfig;
//import kafka.consumer.ConsumerIterator;
//import kafka.consumer.KafkaStream;
//import kafka.javaapi.consumer.ConsumerConnector;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
public class MsgConsumer{

     public static void main(String[] args) {
//         MsgConsumer kc =new MsgConsumer();
//         kc.testConsumer();

         Properties props = new Properties();

         props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "39.106.135.201:2181");
         props.put(ConsumerConfig.GROUP_ID_CONFIG ,"USER") ;
         props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
         props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
         props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
         props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

         Consumer<String, String> consumer = new KafkaConsumer<>(props);
         consumer.subscribe(Arrays.asList("page_visits"));

         while (true) {
             ConsumerRecords<String, String> records = consumer.poll(100);
             for (ConsumerRecord<String, String> record : records) {
                 System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
             }
         }
    }
//    public void testConsumer()
//    {
//        b("test");
//    }
//    private void b(String topic)
//    {
//        ConsumerConnector consumer = createConsumer();
//        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
//        topicCountMap.put(topic, 1); // 一次从主题中获取一个数据  
//        Map<String, List<KafkaStream<byte[], byte[]>>>messageStreams = consumer.createMessageStreams(topicCountMap);
//        KafkaStream<byte[], byte[]> stream = messageStreams.get(topic).get(0);// 获取每次接收到的这个数据  
//        ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        while(iterator.hasNext()){
//            String message = new String(iterator.next().message());
//            System.out.println("接收到时间："+ df.format(new Date())+",message: " + message);
//        }
//    }
//
//    private ConsumerConnector createConsumer() {
//        Properties props = new Properties();
//        props.put("zookeeper.connect", "39.106.135.201:2181");//声明zk  
//        props.put("group.id", "USER");
//        return Consumer.createJavaConsumerConnector(new ConsumerConfig(props));
//    }
}