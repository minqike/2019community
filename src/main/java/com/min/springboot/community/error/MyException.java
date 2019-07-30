package com.min.springboot.community.error;

public class MyException extends RuntimeException {
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 接受errorCode接口对象,得到code和msg
     * @param errorCode
     */
    public MyException(IMyErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
