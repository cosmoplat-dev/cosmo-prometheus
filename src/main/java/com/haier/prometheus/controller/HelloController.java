package com.haier.prometheus.controller;

import com.haier.prometheus.annotation.HttpMethodMetric;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @HttpMethodMetric(name = "hi", description = "hi",tags = {"cosom-prometheus","hello-controller"})
    @GetMapping("/hi")
    public String hi(){
        return "hello";
    }

}
