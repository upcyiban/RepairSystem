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
        return "admin";
    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public String login(String username, String password, Model model) {
        if (username.equals(DevConfig.adminUsername) && password.equals(DevConfig.admiPassword)) {
            Iterable<RepairList> repairList = repairListDao.findAll();
            model.addAttribute("adminLists", repairList);
            return "message";
        } else {
            String result = "出错了！";
            String word = "账号到期或密码有误";
            model.addAttribute("tittle", result);
            model.addAttribute("word", word);
            return "message";
        }
    }

    @RequestMapping("/detail")
    public String showDetail(int yibanid, Model model) {
        RepairList repairList = repairListDao.findByYibanid(yibanid);
        model.addAttribute("repairList", repairList);
        String state = null;
        if (repairList.getState() == 0) {
            state = "未确认";
        } else if (repairList.getState() == 1) {
            state = "已确认";
        }else f(repairList.getState()==2){
            state = "已维修";
        }
        model.addAttribute("state", state);
        return "detail";
    }

    @RequestMapping(value = "/cheack",method = RequestMethod.POST)
    public String cheack(int id,String pass,Model model){
        if (pass.equals("确认")){
            RepairList repairList = repairListDao.findById(id);
            repairList.setState(1);
            repairListDao.save(repairList);
        }else if(pass.equals("维修")){
            RepairList repairList = repairListDao.findById(id);
            repairList.setState(2);
            repairListDao.save(repairList);
        }else if(pass.equals("删除")){
            repairListDao.delete(id);
        }
        Iterable<RepairList> repairList = repairListDao.findAll();
        model.addAttribute("adminLists",repairList);
        return "admin";
    }
}




