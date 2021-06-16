package com.ckj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EncodingController {
    @GetMapping("/encoding/t1")
    public String encodingTest1(String name,Model model){
        model.addAttribute("msg",name);
        return "test";
    }
    @PostMapping("/encoding/t1")
    public String encodingTest2(String name,Model model){
        model.addAttribute("msg",name);
        return "test";
    }
}
