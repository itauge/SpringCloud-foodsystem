package com.itauge.controller;

import com.itauge.entity.Menu;
import com.itauge.entity.Type;
import com.itauge.repository.MenuRepository;
import com.itauge.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuHandler {

    @Autowired
    MenuRepository menurepository;
    @Autowired
    TypeRepository typeRepository;

    @GetMapping("/findall/{index}/{limit}")
    public List<Menu> findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        return menurepository.findAll(index,limit);
    }

    @GetMapping("/countall")
    public int countAll(){
        return menurepository.count();
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
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
