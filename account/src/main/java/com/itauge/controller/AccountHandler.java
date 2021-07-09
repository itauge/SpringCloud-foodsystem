package com.itauge.controller;

import com.itauge.repository.AdminRepository;

import com.itauge.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountHandler {

    @Autowired
    public UserDao userDao;

    @Autowired
    public AdminRepository adminRepository;

    @GetMapping("/login/{username}/{password}/{type}")
    public Object login(@PathVariable("username") String username,
                        @PathVariable("password") String password,
                        @PathVariable("type") String type){
        Object object = null;
        switch (type){
            case "user":
                object = userDao.login(username,password);
                break;
            case "admin":
                object = adminRepository.login(username,password);
                break;
        }

        return object;

    }

}
