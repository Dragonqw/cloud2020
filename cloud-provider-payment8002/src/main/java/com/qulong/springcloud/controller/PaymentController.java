package com.qulong.springcloud.controller;

import com.qulong.springcloud.entities.CommonResult;
import com.qulong.springcloud.entities.Payment;
import com.qulong.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;


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
        if(payment!=null)
        {
            return new CommonResult(200,"查询成功 server.port: "+port,payment);
        }else {
            return new CommonResult(444,"查询失败");
        }


    }
}
