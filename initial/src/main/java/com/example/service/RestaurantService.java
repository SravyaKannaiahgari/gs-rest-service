package com.example.service;

import com.example.mappers.RestaurantMapper;
import com.example.model.entity.Item;
import com.example.model.entity.Restaurant;
import com.example.model.entity.RestaurantItemMapping;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantService {

    @Autowired
    RestaurantMapper restaurantMapper;
    public Integer createRestaurant(Restaurant restaurant) {
        restaurantMapper.saveRestaurant(restaurant);
        Integer restaurantId = restaurantMapper.getLastInsertId();
        for (Item item: restaurant.getMenu()) {
            restaurantMapper.saveItem(item);
            Integer itemId = restaurantMapper.getLastInsertId();
            restaurantMapper.saveRestaurantItemMapping(restaurantId, itemId);
        }
        return restaurantId;
    }

    public Restaurant getRestaurant(Integer id) {
        Restaurant restaurant = restaurantMapper.getRestaurantById(id);
        if(restaurant == null) {
            return null;
        }
        List<RestaurantItemMapping> menuItemMappings = restaurantMapper.getRestaurantToItemMappings(restaurant.getId());
        List<Item> menu = new ArrayList<>();
        for(RestaurantItemMapping itemMapping: menuItemMappings) {
            Integer itemId = itemMapping.getItemId();
            Item item = restaurantMapper.getMenuItem(itemId);
            menu.add(item);
        }
        restaurant.setMenu(menu);
        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantMapper.getRestaurants();
        if(restaurants == null) {
            return null;
        }
        for(Restaurant restaurant : restaurants){
            List<RestaurantItemMapping> menuItemMappings = restaurantMapper.getRestaurantToItemMappings(restaurant.getId());
            List<Item> menu = new ArrayList<>();
            for(RestaurantItemMapping itemMapping: menuItemMappings) {
                Integer itemId = itemMapping.getItemId();
                Item item = restaurantMapper.getMenuItem(itemId);
                menu.add(item);
            }
            restaurant.setMenu(menu);
        }
        return restaurants;
    }
    public boolean deleteRestaurant(Integer id) {
        List<RestaurantItemMapping> menuItemMappings = restaurantMapper.getRestaurantToItemMappings(id);
        for(RestaurantItemMapping itemMapping: menuItemMappings) {
            restaurantMapper.deleteItem(itemMapping.getItemId());
            restaurantMapper.deleteRestaurantItemMapping(itemMapping.getRestaurantId(), itemMapping.getItemId());
        }
        Integer rowCount = restaurantMapper.deleteRestaurant(id);
        if(rowCount == 0) {
            return  false;
        }
        return true;
    }
}
