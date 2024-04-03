package com.wym.demo.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result<T> {
 
    private Integer code; //编码：1成功，0和其它数字为失败
 
    private String msg; //错误信息

    private String token;
 
    private T data; //数据
 
    private Map map = new HashMap(); //动态数据
 


    public static <T> Result<T> success(String token) {
        Result<T> r = new Result<T>();
        r.code = 200;
        r.token = token;
        return r;
    }
 
    public static <T> Result<T> error(String msg) {
        Result r = new Result();
        r.msg = msg;
        r.code = 0;
        return r;
    }
 
    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
 
}