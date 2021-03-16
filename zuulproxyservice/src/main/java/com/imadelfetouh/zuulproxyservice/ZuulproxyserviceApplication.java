package com.imadelfetouh.zuulproxyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ZuulproxyserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulproxyserviceApplication.class, args);
    }

}
