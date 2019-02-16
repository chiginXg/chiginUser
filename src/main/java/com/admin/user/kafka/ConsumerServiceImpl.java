package com.admin.user.kafka;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Override
    public void pollMessage(List<String> messages) {
        System.out.println("===="+messages);
    }
}
