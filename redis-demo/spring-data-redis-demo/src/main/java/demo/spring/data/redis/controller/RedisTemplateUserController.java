package demo.spring.data.redis.controller;

import demo.spring.data.redis.entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * RedisTemplate<String, Object> demo
 *
 * @author wangzefeng
 */
@RestController
@RequestMapping("/redis-template/user")
public class RedisTemplateUserController {

    /**
     * Redis key namespace
     */
    public static final String NAME_SPACE = "demo:user:";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping
    public void saveUser(@RequestBody User user) {
        redisTemplate.opsForValue().set(NAME_SPACE + user.getId(), user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        Object obj = redisTemplate.opsForValue().get(NAME_SPACE + id);

        if (obj instanceof User) {
            return (User) obj;
        }
        return null;
    }

}
