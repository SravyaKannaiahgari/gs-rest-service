package com.example.mappers;

import com.example.model.entity.Item;
import com.example.model.entity.Restaurant;
import com.example.model.entity.RestaurantItemMapping;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface RestaurantMapper {

    @Insert("Insert into Restaurant(name, cuisine) values (#{name}, #{cuisine})")
    public Integer saveRestaurant(Restaurant restaurant);

    @Insert("Insert into Item(name, image, available) values (#{name}, #{image}, #{available})")
    public Integer saveItem(Item item);

    @Insert("Insert into RestaurantItemMapping(restaurantId, itemId) values(#{restaurantId}, #{itemId})")
    public void saveRestaurantItemMapping(Integer restaurantId, Integer itemId);

    @Select(
            "Select id, name, cuisine from Restaurant where id=#{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property=  "cuisine", column = "cuisine"),
    })
    public Restaurant getRestaurantById(Integer id);

    @Select("Select itemId, restaurantId from RestaurantItemMapping where restaurantId=#{restaurantId}")
    @Results(value = {
            @Result(property = "itemId", column = "itemId"),
            @Result(property = "restaurantId", column = "restaurantId"),
    })
    public List<RestaurantItemMapping> getRestaurantToItemMappings(Integer restaurantId);

    @Select(
            "Select id, name, image, available from Item where id=#{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "image", column = "image"),
            @Result(property=  "available", column = "available"),
    })
    public Item getMenuItem(Integer id);

    @Select("Select last_insert_id()")
    public Integer getLastInsertId();
}
