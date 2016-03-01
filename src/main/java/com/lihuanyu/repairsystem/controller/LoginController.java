package com.lihuanyu.repairsystem.controller;

import com.google.gson.Gson;
import com.lihuanyu.repairsystem.conflg.DevConfig;
import com.lihuanyu.repairsystem.service.SaveSessionService;
import com.lihuanyu.repairsystem.session.SessionUser;
import com.lihuanyu.repairsystem.util.MCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by echao on 2016/2/28.
 */
@RestController
public class LoginController {

    @Autowired
    private SaveSessionService saveSessionService;

    @Autowired
    private HttpSession httpsession;

    @RequestMapping(value = "/",method = RequestMethod.GET,params = "verify_request")
    public String repariSystem(String verify_request) throws Exception{
        MCrypt mCrypt = new MCrypt();
        String output = new String(mCrypt.decrypt(verify_request));
        Gson gson = new Gson();
        SessionUser sessionUser = gson.fromJson(output, SessionUser.class);
        saveSessionService.saveSession(sessionUser);
        return output;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView testOauth(){
        String userid = (String) httpsession.getAttribute("userid");
        if(userid!=null){
            ModelAndView mv1 = new ModelAndView("index");
            return mv1;
        }
        ModelAndView mv = new ModelAndView("redirect:https://openapi.yiban.cn/oauth/authorize?client_id="+ DevConfig.client_id + "&redirect_uri=" + DevConfig.redirect_uri);
        return mv;
    }
}
