package com.itauge.controller;

import com.itauge.entity.Menu;
import com.itauge.entity.Type;
import com.itauge.repository.Menurepository;
import com.itauge.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuHandler {

    @Autowired
    Menurepository menurepository;
    @Autowired
    TypeRepository typeRepository;

    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index(){
        return this.port;
    }

    @GetMapping("/findall/{index}/{limit}")
    public List<Menu> findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        return menurepository.findAll(index,limit);
    }

    @GetMapping("/countall")
    public int countAll(){
        return menurepository.count();
    }

    @GetMapping("/deleteById/{id}")
    public void deleteByid(@PathVariable("id") long id){
        menurepository.deleteById(id);
    }

    @GetMapping("/findTypes")
    public List<Type> findAll(){
        return typeRepository.findAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody Menu menu){
        menurepository.save(menu);
    }

    @GetMapping("/findById/{id}")
    public Menu findById(@PathVariable("id") long id){
        return menurepository.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Menu menu){
        menurepository.update(menu);
    }
}
