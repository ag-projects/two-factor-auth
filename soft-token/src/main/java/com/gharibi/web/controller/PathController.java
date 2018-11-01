package com.gharibi.web.controller;

import org.springframework.stereotype.Controller;

@Controller
public class PathController {

    public String home() {
        return "redirect:/user";
    }
}
