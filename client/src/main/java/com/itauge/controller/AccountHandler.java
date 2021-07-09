package com.itauge.controller;

import com.itauge.entity.Admin;
import com.itauge.entity.User;
import com.itauge.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/account")
public class AccountHandler {

    @Autowired
    AccountFeign accountFeign;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("type") String type,
                        HttpSession session){

        Object object = accountFeign.login(username,password,type);

        String result = null;
        if(object == null){
            result = "login";
        }else {
            switch (type){
                case "user":
                    session.setAttribute("user",object);
                    result = "index";
                    break;
                case "admin":
                    session.setAttribute("admin",object);
                    result = "menu_manager";
                    break;
            }
        }

        return result;
    }
}
