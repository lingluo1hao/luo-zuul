package com.zuul.luo.zuul;

import com.zuul.luo.zuul.filter.SignVaildFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy
@EnableHystrix
@RestController

public class LuoZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuoZuulApplication.class, args);
    }


    @Bean
    public SignVaildFilter singnValidFilter(){
        return new SignVaildFilter();
    }


    @GetMapping("hello")
    public Object hello(){
        return "hello springCloud!";
    }
}
