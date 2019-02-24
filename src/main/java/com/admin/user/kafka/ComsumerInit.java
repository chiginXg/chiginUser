package com.admin.user.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class ComsumerInit {
//    @PostConstruct
    public void start(){
        new Thread(()->{
            new MsgConsumer();
        }).start();
        log.info("------------启动kafka消费者开始-----------");
    }
}
