package com.security.SpringSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hr")
public class HrController {

    @GetMapping("/")
    public String hrDashboard (){
        return "Hr related Dashboard";
    }
}
