package com.admin.user.kafka;

import java.util.List;

/**
 * 预留消费接口
 */
public interface ConsumerService {
    void pollMessage(List<String> messages);
}
