package com.min.springboot.community.error;

public enum MyErrorCode  implements IMyErrorCode{

    QUESTION_NOT_FOUND(1001,"问题不存在,请重试其他"),
    COMMENT_NOT_FOUND(1002,"回复不存在"),
    USER_NOT_SAME(1003,"用户不一致,无法保存"),
    NON_USER(1004, "匿名用户评论,无法修改");

    private Integer code;
    private String message;

    //构造方法
    MyErrorCode(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
