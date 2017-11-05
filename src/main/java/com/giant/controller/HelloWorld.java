package com.giant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorld {
    //,produces = "text/html;charset=utf-8"
    @RequestMapping(value = "hello")
    @ResponseBody
    public String HelloWorld(){
        return "Hello World!";
    }
}
