package com.min.springboot.community.error;

/**
 * 错误信息接口类,为错误信息enum类做通用设计,目的是错误信息可以放到各个不同的enum文件中.
 * 新创建的错误信息enum类,只要实现了该接口,定义的错误类MyException中能接受该接口实现IMyErrorCode的对象
 *
 */
public interface IMyErrorCode {
    Integer getCode();
    String getMessage();
}
