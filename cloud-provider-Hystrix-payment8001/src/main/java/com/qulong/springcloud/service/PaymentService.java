package com.qulong.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id){
        return "线程:"+Thread.currentThread().getName()+"paymentInfo_OK,id"+id+"哈哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOut_Handler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id){
        int timeout=1;
        //int a=10/0;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "线程:"+Thread.currentThread().getName()+"paymentInfo_TimeOut,id"+id+"哈哈哈~"+"\t"+"耗时:"+timeout;
    }

    public String paymentInfo_TimeOut_Handler(Integer id){
        return "线程:"+Thread.currentThread().getName()+"paymentInfo_TimeOut_Handler,id"+id+"呜呜呜~";
    }

    //**********服务熔断

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_Handler",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value ="true"),//开启熔断器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value ="10" ),//请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")//失败请求的百分比
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id<0){
            throw new RuntimeException("*****id  不能为负数");
        }
        String serial= IdUtil.simpleUUID();
        return "线程："+Thread.currentThread().getName()+"\t"+"调用流水号为："+serial;
    }


    public String paymentCircuitBreaker_Handler(@PathVariable("id") Integer id){
        return "id不能为负数，请稍后再试。。。。。id:"+id;
    }

}
