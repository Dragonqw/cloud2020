package com.qulong.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced  //LoadBalanced开启RestTemplate的负载均衡功能
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
