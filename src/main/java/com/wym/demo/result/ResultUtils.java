package com.wym.demo.result;


import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

public class ResultUtils {

    private String message;
    private String error;
    private String token;
    private Object obj;

    public ResultUtils(final String message) {
        super();
        this.message = message;
    }

    public ResultUtils(final String message, final String error) {
        super();
        this.message = message;
        this.error = error;
    }

    public ResultUtils(final String message, final String token,final String error,final Object obj) {
        super();
        this.message = message;
        this.token = token;
        this.error = error;
        this.obj = obj;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
