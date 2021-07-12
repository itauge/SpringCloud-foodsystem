package com.itauge.controller;

import com.itauge.entity.Order;
import com.itauge.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderHandler {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/save")
    public void save(@RequestBody Order order){
        order.setDate(new Date());
        orderRepository.save(order);
    }

    @GetMapping("/findAllByUid/{index}/{limit}/{uid}")
    public List<Order> findAllByUid(@PathVariable("index")int index,
                                    @PathVariable("limit") int limit,
                                    @PathVariable("uid") int uid){
        return orderRepository.findAllByUid(index,limit,uid);
    }

    @GetMapping("/countByUid/{uid}")
    public int countByUid(@PathVariable("uid") int uid){
        return orderRepository.countByUid(uid);
    }

    @GetMapping("/findall/{index}/{limit}")
    public List<Order> findAll(@PathVariable("index") int index,
                               @PathVariable("limit") int limit){
        return orderRepository.findAllByState(index,limit);
    }

    @GetMapping("/updateState/{id}")
    public void updateState(@PathVariable("id") long id){
        orderRepository.updateState(id);
    }

    @GetMapping("/countByState")
    public int countByState(){
        return orderRepository.countByState();
    }


}
