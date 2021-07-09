package com.itauge.repository;

import com.itauge.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    public User login(String username,String password);
}
