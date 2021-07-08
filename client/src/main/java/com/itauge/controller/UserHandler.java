package com.itauge.controller;

import com.itauge.entity.User;
import com.itauge.entity.UserVO;
import com.itauge.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location){
        return location;
    }

    @ResponseBody
    @GetMapping("/findall")
    public UserVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        UserVO userVO = new UserVO(0, "", userFeign.count(), userFeign.findAll(index, limit));
        return userVO;
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") long id){
        return userFeign.findById(id);
    }

    @GetMapping("/count")
    public int count(){
        return userFeign.count();
    }

    @PostMapping("/save")
    public String save(User user){
        userFeign.save(user);
        return "redirect:/user/redirect/user_manager";
    }

    @PutMapping("/update")
    public void update(User user){
        userFeign.update(user);
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
        userFeign.deleteById(id);
        return "redirect:/user/redirect/user_manager";
    }

}
