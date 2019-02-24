package com.admin.user.kafka;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

//@Component
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

    public static void main(String[] args) {
            //存数据
            Jedis jedis = new Jedis("localhost",6379);
            jedis.auth("123456");
            jedis.select(9);
            for (int i = 0; i < 10; i++) {
                String s = UUID.randomUUID().toString();
                for (int j = 10 - i; j > 0; j--) {
                    System.out.println(j);
                    //数组中存值  j:排序值，s:对应数组里key
                    jedis.zadd("list", j, s);
                    break;

                }
                Date date = new Date();
                SolrBean solrBean = new SolrBean();
                solrBean.setId(s);
                solrBean.setContent(date);
                String jsonObject = JSONObject.toJSONString(solrBean);
                Long test = jedis.hset("test", s, jsonObject);
                boolean d = test >= 0;

            }
            String ss = "http://10.0.0.1:80/";
            int i = ss.indexOf("/", 3);

        //分页取数据
            Set<String> set = jedis.zrevrangeByScore("list", "+inf", "-inf",2,5);
            System.out.println(set);

    }

    @Data
    public static class SolrBean{
        private String id;
        private Date Content;
    }
}
