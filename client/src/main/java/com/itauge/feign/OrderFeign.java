package com.itauge.feign;

import com.itauge.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(value = "order")
public interface OrderFeign {

    @PostMapping("/order/save")
    public void save(Order order);

    @GetMapping("/order/findAllByUid/{index}/{limit}/{uid}")
    public List<Order> findAllByUid(@PathVariable("index") int index,
                                    @PathVariable("limit") int limit,
                                    @PathVariable("uid") int uid);

    @GetMapping("/order/countByUid/{uid}")
    public int countByUid(@PathVariable("uid") int uid);

    @GetMapping("/order/countByState")
    public int countByState();

    @GetMapping("/order/findall/{index}/{limit}")
    public List<Order> findAll(@PathVariable("index") int index,@PathVariable("limit") int limit);

    @GetMapping("/order/updateState/{id}")
    public void updateState(@PathVariable("id") long id);

}
