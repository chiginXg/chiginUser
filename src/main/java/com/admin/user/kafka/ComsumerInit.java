package com.admin.user.kafka;


import javax.annotation.PostConstruct;

public class ComsumerInit {
    @PostConstruct
    public void start(){
        new Thread(()->{
            new MsgConsumer();
        }).start();
    }
}
