package demo.spring.data.redis.controller;

import demo.spring.data.redis.entity.User;
import demo.spring.data.redis.util.JsonUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * StringRedisTemplate Demo
 *
 * @author wangzefeng
 */
@RestController
@RequestMapping("/string-redis-template/user")
public class StringRedisTemplateUserController {

    public static final String NAME_SPACE = RedisTemplateUserController.NAME_SPACE;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping
    public void saveUser(@RequestBody User user) {
        stringRedisTemplate.opsForValue().set(NAME_SPACE + user.getId(), JsonUtil.writeValueAsString(user));
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        String jsonStr = stringRedisTemplate.opsForValue().get(NAME_SPACE + id);
        return JsonUtil.readValue(jsonStr, User.class);
    }

}
