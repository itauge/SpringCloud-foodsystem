package com.itauge.feign;

import com.itauge.entity.Menu;
import com.itauge.entity.Type;
import com.itauge.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(value = "user")
public interface UserFeign {
    @GetMapping("/user/findall/{index}/{limit}")
    public List<User> findAll(@PathVariable("index") int index, @PathVariable("limit") int limit);

    @GetMapping("/user/count")
    public int count();

    @GetMapping("/user/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id);

    @PostMapping("/user/save")
    public void save(User user);

    @GetMapping("/user/findById/{id}")
    public User findById(@PathVariable("id") long id);

    @PutMapping("/user/update")
    public void update(User user);

}
