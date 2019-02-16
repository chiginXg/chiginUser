package com.admin.user.kafka;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class ConsumerTest implements ConsumerService {
    @Scheduled(cron = "/10 * * * * ?")
    @Override
    public void pollMessage(List<String> messages) {
        System.out.println("消费者消费："+messages);
    }


}
