package com.rocky.WorldCupPredictor26.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/predict")
    public String predict() {
        return "predict";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
}