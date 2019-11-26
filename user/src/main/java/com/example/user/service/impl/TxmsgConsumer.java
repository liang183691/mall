package com.example.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "SELF_TEST_TOPIC",consumerGroup = "rocketmq_transaction_default_global_name")
@Slf4j
public class TxmsgConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.info("开始消费消息:{}",s);
        //int a = 1 / 0;
        //解析消息为对象
        final JSONObject jsonObject = JSON.parseObject(s);

    }
}
