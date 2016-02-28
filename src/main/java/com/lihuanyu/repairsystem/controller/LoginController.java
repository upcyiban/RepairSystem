package com.lihuanyu.repairsystem.controller;

import com.lihuanyu.repairsystem.conflg.DevConfig;
import com.lihuanyu.repairsystem.util.MCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by echao on 2016/2/28.
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/",method = RequestMethod.GET,params = "verify_request")
    public String repariSystem(String verify_request) throws Exception{
        MCrypt mCrypt = new MCrypt();
        String output = new String(mCrypt.decrypt(verify_request));
        return output;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView testOauth(){
        ModelAndView mv = new ModelAndView("redirect:https://openapi.yiban.cn/oauth/authorize?client_id="+ DevConfig.client_id + "&redirect_uri=" + DevConfig.redirect_uri);
        return mv;
    }
}
