package com.sush.Quizapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/test")
    public String firstone(Model themodel){
        themodel.addAttribute("thedate", java.time.LocalDateTime.now());
        return "hello";
    }
}
