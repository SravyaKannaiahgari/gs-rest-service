package com.example.model.entity;

import com.example.restservice.RestaurantManagementController;

import java.util.List;

public class Restaurant {
    private String name;
    private String cuisine;
    private List<Item> menu;
    private Integer id;

    public String getCuisine() {
        return cuisine;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public List<Item> getMenu() {
        return menu;
    }

    public void setMenu(List<Item> menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
