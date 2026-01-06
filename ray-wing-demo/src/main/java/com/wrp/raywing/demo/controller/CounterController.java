package com.wrp.raywing.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wrp
 * @since 2026年01月06日 11:02
 **/
@RestController
@RequestMapping
public class CounterController {

//    private int counter = 0;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 统计访问人数
     */
    @GetMapping
    public String count() {
        Long count = stringRedisTemplate.opsForValue().increment("count");
        return "有【" + count + "】人访问了这个页面";
    }
}
