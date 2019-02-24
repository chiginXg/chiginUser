package com.admin.user.filter;

import com.admin.user.kafka.KafkaConfig;
import com.admin.user.kafka.MsgProducer;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class UserApplicationRunner implements ApplicationRunner {

//    @Autowired
//    private KafkaConfig kafkaConfig;

    /**
     * 压缩空间
     * 防止黑客恶意攻击，缓存穿透等问题
     * 爬虫URL地址去重
     */
    private static final BloomFilter<String> boomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 100,0.001);

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
