package com.lihuanyu.repairsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by echao on 2016/3/3.
 */
@Controller
public class StaticController {

    @RequestMapping("/about")
    public String showAbout(){
        return "about";
    }

    @RequestMapping("/contact")
    public String showConyact(){
        return "contact";
    }
}
