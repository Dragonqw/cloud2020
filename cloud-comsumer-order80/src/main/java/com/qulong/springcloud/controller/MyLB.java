package com.qulong.springcloud.controller;

import org.springframework.stereotype.Service;

@Service
public class MyLB implements LoadBalance {
    @Override
    public String postString(String qw) {
        return qw+"瞿龙";
    }
}
