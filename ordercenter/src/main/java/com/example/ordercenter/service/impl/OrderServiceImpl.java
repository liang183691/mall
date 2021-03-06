package com.example.ordercenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.ordercenter.entity.Order;
import com.example.ordercenter.mapper.OrderMapper;
import com.example.ordercenter.message.RunTimeUtil;
import com.example.ordercenter.service.OrderService;
import com.example.user.entity.User;
import com.liang.framework.springmqstarter.rabbitmq.FirstSender;
import org.apache.http.client.utils.URIBuilder;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FirstSender firstSender;

    @Resource
    RocketMQTemplate rocketMQTemplate;

    @Override
    public int addOrder(Order order) {
        try {
            firstSender.send("order",JSON.toJSONString(order));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public int addOrder1(Order order) {
        /*TransactionMQProducer transactionMQProducer = new TransactionMQProducer();
        String s =transactionMQProducer.getCreateTopicKey();
        System.out.println(s);
        transactionMQProducer.setInstanceName(RunTimeUtil.getRocketMqUniqeInstanceName());
        Message<String> message = MessageBuilder.withPayload(JSONObject.toJSONString(order)).build();
        transactionMQProducer.sendMessageInTransaction(message,null);
        //new DefaultMQProducer().getCreateTopicKey();*/
        //生成message类型
        //rocketMQTemplate.removeTransactionMQProducer("producer_my_test_fenbushi_message");
        Message<String> message = MessageBuilder.withPayload(JSONObject.toJSONString(order)).build();
        rocketMQTemplate.sendMessageInTransaction("rocketmq_transaction_default_global_name","SELF_TEST_TOPIC",message,null);
        return 0;
    }


    @Override
    public Order getOrderById(String orderId) {
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme("http")
                    .setPort(8083)
                    .setHost("USER")
                    .setPath("user")
                    .setParameter("userId", "1")
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,null,String.class);
        String responseEntityBody = responseEntity.getBody();
        String userStr = JSON.parseObject(responseEntityBody).getString("data");
        //String user1 = restTemplate.getForObject("http://USER/user?userId={userId}",String.class,1);
        if(userStr == null){
            System.out.println("未查询到该用户");
            return null;
        }
        User user = JSON.parseObject(userStr,User.class);
        String key = "order_" + orderId;
        ValueOperations<String, Order> operations = redisTemplate.opsForValue();
        HashOperations hashOperations = redisTemplate.opsForHash();
        ListOperations listOperations = redisTemplate.opsForList();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            Order order = operations.get(key);
            System.out.println("从缓存中获取订单：" + order.toString());
            return order;
        }
        Order order = orderMapper.selectById(orderId);
        if(order == null){
            return null;
        }
        System.out.println("插入redis缓存：" + order.toString());
        operations.set(key, order, 10, TimeUnit.HOURS);

        /*Map<String,Object> map = new HashMap<>();
        map.put("order",order);
        hashOperations.putAll("hashMap",map);
        System.out.println("hashMap" + hashOperations.get("hashMap","order").toString());

        List list = new ArrayList();
        list.add("1");
        list.add("2");
        listOperations.leftPush("list",list);
        list =  redisTemplate.opsForList().range("list",0,-1);
        list = (List)redisTemplate.opsForList().rightPop("list");
        System.out.println(redisTemplate.hasKey("list"));*/
        return order;
    }


    @RabbitListener(queues = {"first-queue","second-queue"}, containerFactory = "rabbitListenerContainerFactory")
    public void handleMessage(String message) throws Exception {
        // 处理消息
        Order order = null;
        try {
           order  = JSON.parseObject(message,Order.class);
           orderMapper.insert(order);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("FirstConsumer {} handleMessage :"+ order.toString());
    }
}
