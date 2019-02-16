package com.admin.user.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Test implements ApplicationRunner {
    @Autowired
    private ConsumerServiceImpl consumerService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("开始加载");
        MsgProducer msgProducer = new MsgProducer();
        msgProducer.init();
        for (int i = 0; i <10 ; i++) {
            msgProducer.service("message"+i);
        }
        Thread.sleep(1000);

    }

}
