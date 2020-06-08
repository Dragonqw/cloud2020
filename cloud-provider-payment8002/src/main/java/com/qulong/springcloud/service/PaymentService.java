package com.qulong.springcloud.service;

import com.qulong.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int addPayment(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
