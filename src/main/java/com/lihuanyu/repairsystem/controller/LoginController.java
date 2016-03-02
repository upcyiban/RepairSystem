package com.lihuanyu.repairsystem.controller;

import com.google.gson.Gson;
import com.lihuanyu.repairsystem.conflg.DevConfig;
import com.lihuanyu.repairsystem.model.RepairList;
import com.lihuanyu.repairsystem.model.RepairListDao;
import com.lihuanyu.repairsystem.service.SaveSessionService;
import com.lihuanyu.repairsystem.session.SessionUser;
import com.lihuanyu.repairsystem.util.MCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by echao on 2016/2/28.
 */
@Controller
public class LoginController {

    @Autowired
    private SaveSessionService saveSessionService;

    @Autowired
    private HttpSession httpsession;

    @Autowired
    private RepairListDao repairListDao;

    @RequestMapping(value = "/",method = RequestMethod.GET,params = "verify_request")
    public String repariSystem(String verify_request) throws Exception{
        MCrypt mCrypt = new MCrypt();
        String output = new String(mCrypt.decrypt(verify_request));
        Gson gson = new Gson();
        SessionUser sessionUser = gson.fromJson(output, SessionUser.class);
        saveSessionService.saveSession(sessionUser);
        return "redirect:/";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String testOauth(Model model){
        String username = (String) httpsession.getAttribute("username");
        if(username!=null){
            Iterable<RepairList> repairList = repairListDao.findByYibanname(username);
            model.addAttribute("repairList",repairList);
            model.addAttribute("username",username);
            return "index";
        }
        return "redirect:https://openapi.yiban.cn/oauth/authorize?client_id="+ DevConfig.client_id + "&redirect_uri=" + DevConfig.redirect_uri;
    }
}
