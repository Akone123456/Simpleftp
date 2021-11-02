package com.xzit.simpleftp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/file")
    public String file(){
        return "file";
    }
}
