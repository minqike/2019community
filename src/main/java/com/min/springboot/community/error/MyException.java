package com.min.springboot.community.error;

public class MyException extends RuntimeException {
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 接受errorCode接口对象,得到code和msg
     * @param errorCode
     */
    public MyException(IMyErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }
}
