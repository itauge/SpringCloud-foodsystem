package com.itauge.repository;

import com.itauge.entity.Type;

import java.util.List;

public interface TypeDao {
    public Type findById(int id);
    public List<Type> findAll();
}
