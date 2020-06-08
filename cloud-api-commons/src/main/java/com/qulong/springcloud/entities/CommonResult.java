package com.qulong.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 将数据传给前端的类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;       //类似于404错误的编码
    private String message;     //消息
    private T data;             //传给前端的实体类的信息


    //这个构造方法是防止实习类为空
    public CommonResult(Integer code,String message){
        this(code, message, null);
    }
}
