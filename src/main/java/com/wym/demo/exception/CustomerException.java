package com.wym.demo.exception;

import com.wym.demo.result.MessageCode;

public class CustomerException extends Exception {
    private String message;
    private Integer code;
    public CustomerException(MessageCode messageCode){
        this.message=messageCode.getMessage();
        this.code=messageCode.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
