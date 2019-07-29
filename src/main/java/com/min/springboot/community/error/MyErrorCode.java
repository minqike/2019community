package com.min.springboot.community.error;

public enum MyErrorCode  implements IMyErrorCode{

    QUESTION_NOT_FOUND(1001,"问题不存在,请重试其他"),;

    private Integer code;
    private String msg;

    //构造方法
    MyErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
