package com.wym.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class Hello{
    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "<h1>测试是否联通</h1>";
    }

}
