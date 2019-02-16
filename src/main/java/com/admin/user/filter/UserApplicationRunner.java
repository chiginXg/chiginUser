package com.admin.user.filter;

import com.admin.user.kafka.KafkaConfig;
import com.admin.user.kafka.MsgProducer;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class UserApplicationRunner implements ApplicationRunner {

//    @Autowired
//    private KafkaConfig kafkaConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("开始加载kafka");
        MsgProducer msgProducer = new MsgProducer();
        msgProducer.init();
//        System.out.println("kafka配置为"+ JSON.toJSONString(kafkaConfig));
    }
}
