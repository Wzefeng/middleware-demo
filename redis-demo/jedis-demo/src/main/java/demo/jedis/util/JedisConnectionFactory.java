package demo.jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * Jedis Connection Factory
 *
 * @author wangzefeng
 */
public class JedisConnectionFactory {

    private static final JedisPool JEDIS_POOL;

    static {
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMinIdle(0);
        config.setMaxIdle(8);
        config.setMaxTotal(8);
        config.setMaxWait(Duration.ofSeconds(1));

        JEDIS_POOL = new JedisPool(config, "192.168.137.129", 6379, 1000, "123456");
    }


    public static Jedis getConnection() {
        return JEDIS_POOL.getResource();
    }
}
