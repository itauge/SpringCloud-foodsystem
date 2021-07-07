package com.itauge.controller;

import com.itauge.entity.Menu;
import com.itauge.entity.MenuVO;
import com.itauge.feign.MenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientHandler {

    @Autowired
    private MenuFeign menuFeign;

    @ResponseBody
    @GetMapping("/findall")
    public MenuVO findAll(@RequestParam("page")int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        List<Menu> all = menuFeign.findAll(index,limit);
        int count = menuFeign.count();
        MenuVO menuVO = new MenuVO(0, "",count, all);
        return menuVO;
    }

    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location){
        return location;
    }
}
