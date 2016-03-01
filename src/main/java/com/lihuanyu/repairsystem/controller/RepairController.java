package com.lihuanyu.repairsystem.controller;

import com.lihuanyu.repairsystem.model.RepairList;
import com.lihuanyu.repairsystem.model.RepairListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by echao on 2016/3/1.
 */
@Controller
public class RepairController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private RepairListDao repairListDao;

    @RequestMapping("/create")
    public String create(Model model){
        String username = (String) httpSession.getAttribute("username");
        int userid = (int) httpSession .getAttribute("userid");
        model.addAttribute("username",username);
        model.addAttribute("userid",userid);
        return "create";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(String message,Model model){
        if (message==null){
            model.addAttribute("result","false");
            return "result";
        }
        RepairList repairList = new RepairList();
        repairList.setYibananme((String) httpSession.getAttribute("username"));
        repairList.setYibanid((Integer) httpSession.getAttribute("userid"));
        repairList.setMessage(message);
        repairList.setState(0);
        repairListDao.save(repairList);
        model.addAttribute("result","success");
        return "result";
    }
}
