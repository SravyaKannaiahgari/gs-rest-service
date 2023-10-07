package com.example.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class RestaurantManagementController {

    List<Restaurant> restaurants = new ArrayList<>();
    Random rand = new Random();
    int count = 0;

    @GetMapping("/v1/restaurants")
    public ResponseEntity<List<Restaurant>> getRestaurants(@RequestParam(required = false) String cuisine) {
        if(cuisine == null) {
            return ResponseEntity.ok(restaurants);
        }

        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for(Restaurant rest : restaurants) {
            if(rest.cuisine != null && rest.cuisine.equals(cuisine)) {
                filteredRestaurants.add(rest);
            }
        }
        return ResponseEntity.ok(filteredRestaurants);
    }

    @GetMapping("/v1/restaurants/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Integer id) {
        if(id == null || id < 0){
            return ResponseEntity.badRequest().build();
        }

        for(Restaurant rest : restaurants) {
            if(rest.id.equals(id)) {
                return ResponseEntity.ok(rest);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/v1/restaurants/{id}/menu/item/{itemId}")
    public ResponseEntity<Item> getMenuItem(@PathVariable Integer id, @PathVariable Integer itemId) {
        if(id == null || id < 0){
            return ResponseEntity.badRequest().build();
        }

        for(Restaurant rest : restaurants) {
            if(rest.id.equals(id)) {
                for(Item item : rest.menu) {
                    if(item.id.equals(itemId)) {
                        return ResponseEntity.ok(item);
                    }
                }
            }
        }

        return ResponseEntity.notFound().build();
    }
    @PostMapping("/v1/restaurants")
    public ResponseEntity<ResourceResponse> createRestaurant(@RequestBody Restaurant restaurant) {
        ResourceResponse resp = new ResourceResponse();
        if(restaurant == null || restaurant.name == null || restaurant.name.isEmpty()) {
            resp.message = "Invalid Input";
            return ResponseEntity.badRequest().body(resp);
        }

        resp.resource = "/v1/restaurants/" + count;
        restaurant.id = count;
        count++;

        restaurants.add(restaurant);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/v1/restaurants/{id}")
    public ResponseEntity<ResourceResponse> deleteRestaurant(@PathVariable Integer id) {

        boolean removed = false;
        for(Restaurant rest : restaurants) {
            if(rest.id.equals(id)) {
                removed = true;
                restaurants.remove(rest);
            }
        }

        if(removed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/v1/restaurants/{id}/menu/item")
    public ResponseEntity<ResourceResponse> addMenuItem(@PathVariable Integer id, @RequestBody Item item) {
        ResourceResponse resResp = new ResourceResponse();
        ResponseEntity<Restaurant> restResp = getRestaurant(id);
        if(restResp.getStatusCode().is2xxSuccessful()) {
            Restaurant rest = restResp.getBody();
            if(rest.menu == null) {
                rest.menu = new ArrayList<>();
            }
            item.id = rand.nextInt(1000000);
            item.available = true;
            rest.menu.add(item);

            resResp.resource = "/v1/restaurants/" + id + "/menu/item/" + item.id;
            return ResponseEntity.ok(resResp);
        }
        return ResponseEntity.notFound().build();
    }


    public static class Restaurant {
        public String name;
        public String cuisine;
        public List<Item> menu;
        public Integer id;
    }

    public static class Item {
        public String name;
        public String image;
        public Integer id;
        public Boolean available;
    }

    public static class ResourceResponse {
        public String resource;
        public String message;
    }
}