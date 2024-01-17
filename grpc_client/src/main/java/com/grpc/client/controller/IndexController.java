package com.grpc.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index(@RequestParam(required = false) String text) {
        if (text != null && text.trim().length() > 0) {
            return text;
        }
        return "Welcome to GRPC client Application";
    }
}
