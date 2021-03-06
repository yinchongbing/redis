package com.example.test;

import com.example.annotation.RedisSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试redis切换库
 * author huangtuL
 */
@RestController
@RequestMapping("redis")
public class TestRedisSelect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/one")
    @RedisSelect(1)         //选择db1库
    public String selectOne(){
        String one = redisTemplate.opsForValue().get("one");
        return one;
    }

    @RequestMapping("/two")
    @RedisSelect(2)         //选择db2库
    public String selectTwo(){
        String two = redisTemplate.opsForValue().get("two");
        return two;
    }
    
    /**
     * 同一个方法中切换不同的redis库
     * @return
     */
    @RequestMapping("/three")
    @RedisSelect(2)         //选择db2库
    public String selectThree(){
        String two = redisTemplate.opsForValue().get("two");
        System.out.println(two);
        RedisSelectSupport.select(3);//此处切换到db3库
        String three = redisTemplate.opsForValue().get("three");
        System.out.println(three);
        return three;
    }
}
