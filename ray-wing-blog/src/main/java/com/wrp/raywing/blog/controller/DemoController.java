package com.wrp.raywing.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wrp
 * @since 2025年08月18日 9:37
 **/
@RestController
public class DemoController {

    @GetMapping("/long/max")
    public Long demo() {
        long maxValue = Long.MAX_VALUE;
        System.out.println(maxValue);
        return maxValue;
    }
}
