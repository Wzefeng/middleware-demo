package demo.jedis;

import demo.jedis.util.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class JedisTest {

    // Redis 服务端信息
    String host = "192.168.137.129";
    int port = 6379;
    String password = "123456";

    Jedis jedis = null;

    // @BeforeEach
    public void init() {
        // 创建 Redis 客户端并建立与服务端的连接
        jedis = new Jedis(host, port);
        // 客户端认证
        jedis.auth(password);
        // 选择库
        jedis.select(0);
    }

    @BeforeEach
    public void initByPool() {
        jedis = JedisConnectionFactory.getConnection();
    }

    @AfterEach
    public void destroy() {
        if (jedis != null) {
            jedis.close();
        }
    }

    @Test
    public void testString() {
        jedis.setnx("name", "Jedis");

        String result = jedis.get("name");
        System.out.println(result);
    }

    @Test
    public void testHash() {
        jedis.hset("user:1", "name", "VN");
        jedis.hset("user:1", "gender", "female");

        Map<String, String> result = jedis.hgetAll("user:1");
        System.out.println(result);
    }

}
