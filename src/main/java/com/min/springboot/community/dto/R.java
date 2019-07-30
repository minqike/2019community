package com.min.springboot.community.dto;

import com.min.springboot.community.error.MyException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {

    private Integer code;
    private String message;
    private Object data;


    public static R ok() {
        return R.ok(null);
    }

    public static R ok(Object obj) {
        R result = new R();
        result.setCode(200);
        result.setMessage("success");
        result.setData(obj);
        return result;
    }

    public static R error(Integer code,String message) {
        R result = new R();
        result.setCode(code);
        result.setMessage(message);
        result.setData("");
        return result;
    }
    public static R error(Integer code,String message,Object obj) {
        R result = new R();
        result.setCode(code);
        result.setMessage(message);
        result.setData(obj);
        return result;
    }

    public static R error(MyException e) {
        return R.error(e.getCode(), e.getMessage());
    }
}
