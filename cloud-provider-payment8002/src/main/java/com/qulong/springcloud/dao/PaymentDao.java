package com.qulong.springcloud.dao;

import com.qulong.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface PaymentDao {
    public int addPayment(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);


}
