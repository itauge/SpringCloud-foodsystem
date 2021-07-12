package com.itauge.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private int id;
    private User user;
    private Menu menu;
    private Admin admin;
    private Date date;
    private int state;
}
