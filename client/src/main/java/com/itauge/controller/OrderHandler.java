package com.itauge.controller;

import com.itauge.entity.Menu;
import com.itauge.entity.Order;
import com.itauge.entity.OrderVO;
import com.itauge.entity.User;
import com.itauge.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderHandler {

    @Autowired
    OrderFeign orderFeign;

    @GetMapping("/save/{mid}")
    public String save(@PathVariable("mid") int mid, HttpSession session) {
            User user = (User) session.getAttribute("user");
            Order order = new Order();
            Menu menu = new Menu();
            menu.setId(mid);
            order.setMenu(menu);
            order.setUser(user);
            orderFeign.save(order);
            return "order";
        }

    @GetMapping("/findAllByUid")
    @ResponseBody
    public OrderVO findAllByUid(@RequestParam("page")int page,
                                @RequestParam("limit")int limit,
                                HttpSession session){
        int index = (page-1)*limit;
        User user = (User) session.getAttribute("user");
        List<Order> all = orderFeign.findAllByUid(index, limit, (int) user.getId());
        OrderVO orderVO = new OrderVO(0, "", orderFeign.countByUid((int) user.getId()), all);
        return orderVO;
    }

    @GetMapping("/findall")
    @ResponseBody
    public OrderVO findAll(@RequestParam("page")int page,
                           @RequestParam("limit")int limit){
        int index = (page-1)*limit;
        List<Order> all = orderFeign.findAll(index, limit);
        OrderVO orderVO = new OrderVO(0,"",orderFeign.countByState(),all);
        return orderVO;
    }

    @GetMapping("/updateState/{id}")
    public String updateState(@PathVariable("id") long id){
        orderFeign.updateState(id);
        return "order_handler";
    }
}
