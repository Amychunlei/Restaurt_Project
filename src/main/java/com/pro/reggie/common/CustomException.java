package com.pro.reggie.common;
/*
 * @Author chunleiAmy
 * @Date 9/6/2022 2:49 PM
 * @Description  自定义业务异常类
 * @Since version-1.0
 */



public class CustomException  extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
