package com.wym.demo.result;

public enum MessageCode {
    SYSTEM_ERROR(50000,"对不起，系统开小差了!"),
    USERNAME_NULL(50001,"用户名不能为空");

    //错误编码
    private Integer code;
    //错误信息
    private String message;
    MessageCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
