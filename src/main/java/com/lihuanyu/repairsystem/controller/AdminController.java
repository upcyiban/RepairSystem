package com.lihuanyu.repairsystem.controller;

import com.lihuanyu.repairsystem.conflg.DevConfig;
import com.lihuanyu.repairsystem.model.RepairList;
import com.lihuanyu.repairsystem.model.RepairListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by echao on 2016/3/1.
 */
@Controller
public class AdminController {

    @Autowired
    private RepairListDao repairListDao;

    @RequestMapping("/admin")
    public String showAdmin() {
        return "loginadmin";
    }

    @RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
    public String login(String username, String password, Model model) {
        if (username.equals(DevConfig.adminUsername) && password.equals(DevConfig.admiPassword)) {
            Iterable<RepairList> repairList = repairListDao.findAll();
            model.addAttribute("adminLists", repairList);
            return "admin";
        } else {
            String result = "出错了！";
            String word = "账号或密码有误";
            model.addAttribute("tittle", result);
            model.addAttribute("word", word);
            return "message";
        }
    }

    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public String cheack(int id,String pass,Model model){
        RepairList repairList = repairListDao.findById(id);
        if (pass.equals("确认")){
            repairList.setState(1);
        }else if(pass.equals("暂不能处理")){
            repairList.setState(2);
        }else if(pass.equals("已处理")){
            repairList.setState(3);
        }
        repairListDao.save(repairList);
        Iterable<RepairList> repairLists = repairListDao.findAll();
        model.addAttribute("adminLists",repairLists);
        return "admin";
    }
}




