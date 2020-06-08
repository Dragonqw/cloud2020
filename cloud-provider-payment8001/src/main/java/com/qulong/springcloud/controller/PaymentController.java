package com.qulong.springcloud.controller;


import com.qulong.springcloud.entities.CommonResult;
import com.qulong.springcloud.entities.Payment;
import com.qulong.springcloud.service.LoadBalance;
import com.qulong.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;


    @Autowired
    private LoadBalance loadBalance;
    @Value("${server.port}")
    private String port;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/add")
    public CommonResult addPayment(@RequestBody Payment payment){

        int result=paymentService.addPayment(payment);
        log.info("插入"+result+"hahahha");
        if(result>0)
        {
            return new CommonResult(200,"插入数据库成功 server.port: "+port,result);
        }else {
            return new CommonResult(444,"插入数据库失败");
        }


    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){

        Payment payment=paymentService.getPaymentById(id);
        log.info("查询成功"+payment);
        System.out.println(loadBalance.postString("qulong13133"));
        if(payment!=null)
        {
            return new CommonResult(200,"查询成功 server.port: "+port,payment);
        }else {
            return new CommonResult(444,"查询失败");
        }


    }

    @GetMapping("/payment/discovery")
    public DiscoveryClient getDiscoveryClient(){
        List<String> services = discoveryClient.getServices();

        for (String element: services) {
            log.info("****element:"+element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        for (ServiceInstance instance: instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri()+"\t"+instance.getInstanceId());
        }
        return this.discoveryClient;
    }
}
