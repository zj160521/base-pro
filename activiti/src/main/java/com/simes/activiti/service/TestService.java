package com.simes.activiti.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.simes.core.domain.test.TestDO;
import com.simes.core.domain.test.TestPara;
import com.simes.activiti.dao.ITestDao;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.*;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 17:48
 */
@Service
public class TestService {
    @Autowired
    private ITestDao dao;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void insert(TestDO testDO) {
        dao.insertSelective(testDO);
        redisTemplate.opsForValue().set("test", JSONObject.toJSONString(testDO));
        String res = redisTemplate.opsForValue().get("test");
        System.out.println(JSON.parseObject(res, TestDO.class).getName());
    }

    public List<TestDO> get() {
        return dao.selectAll();
    }

    public boolean kafkaSend(TestPara para) {
        ListenableFuture<SendResult<String, String>> res = kafkaTemplate.send("boot", JSON.toJSONString(para));
        try {
            SendResult<String, String> result = res.get(2L, TimeUnit.SECONDS);
            if (result != null) {
                RecordMetadata recordMetadata = result.getRecordMetadata();
                if (recordMetadata != null && recordMetadata.hasOffset()) {
                    return true;
                }
                return false;
            }
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return false;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }
}
