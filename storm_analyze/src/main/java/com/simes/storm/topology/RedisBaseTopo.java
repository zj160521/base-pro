package com.simes.storm.topology;

import com.simes.storm.util.ConfigMsg;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
public class RedisBaseTopo {
    private static JedisPool jedisPool;
    public static synchronized JedisPool getJedisPool() {
        if(jedisPool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(1000 * 100);
            config.setMaxWaitMillis(30);
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
            jedisPool = new JedisPool(config, ConfigMsg.HOST,
                    Integer.parseInt(ConfigMsg.REDIS_PORT),
                    2000,
                    ConfigMsg.REDIS_PWD);
        }
        return jedisPool;
    }
}
