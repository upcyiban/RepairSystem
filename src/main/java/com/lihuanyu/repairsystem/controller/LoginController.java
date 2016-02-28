package com.lihuanyu.repairsystem.controller;

import com.lihuanyu.repairsystem.conflg.DevConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by echao on 2016/2/28.
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/",method = RequestMethod.GET,params = "verify_request")
    public String repariSystem() throws Exception{
        return "index";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String testOauth(){

        return "redirect:https://openapi.yiban.cn/oauth/authorize?client_id="+ DevConfig.client_id + "&redirect_uri=" + DevConfig.redirect_uri;
    }
}
