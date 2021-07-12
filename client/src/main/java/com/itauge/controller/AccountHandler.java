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

        Object object = accountFeign.login(username, password, type);
        LinkedHashMap<String,Object> hashMap = (LinkedHashMap)object;

        long id;
        String idStr = null;
        String result = null;
        if(object == null){
            result = "login";
        }else {
            switch (type){
                case "user":
                    User user = new User();
                    idStr = hashMap.get("id")+"";
                    id = Long.parseLong(idStr);
                    String nickname = (String)hashMap.get("nickname");
                    user.setId(id);
                    user.setNickname(nickname);
                    session.setAttribute("user",user);
                    result = "index";
                    break;
                case "admin":
                    Admin admin = new Admin();
                    idStr = hashMap.get("id")+"";
                    id = Long.parseLong(idStr);
                    String username1 = (String) hashMap.get("username");
                    admin.setUsername(username1);
                    session.setAttribute("admin",admin);
                    result = "main";
                    break;
            }
        }

        return result;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }
}
