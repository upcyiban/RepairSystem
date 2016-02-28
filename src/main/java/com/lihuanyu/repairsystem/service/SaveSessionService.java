package com.lihuanyu.repairsystem.service;

import com.lihuanyu.repairsystem.session.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by skyADMIN on 16/2/28.
 */
@Service
public class SaveSessionService {

    @Autowired
    private HttpSession httpSession;

    public void saveSession(SessionUser sessionUser){
        httpSession.setAttribute("visit_time", sessionUser.visit_time);
        httpSession.setAttribute("userid", sessionUser.visit_user.userid);
        httpSession.setAttribute("username", sessionUser.visit_user.username);
        httpSession.setAttribute("usernick", sessionUser.visit_user.usernick);
        httpSession.setAttribute("usersex", sessionUser.visit_user.usersex);
        httpSession.setAttribute("access_token", sessionUser.visit_oauth.access_token);
        httpSession.setAttribute("token_expires", sessionUser.visit_oauth.token_expires);
    }
}
