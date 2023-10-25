package com.example.restservice;

import com.example.model.api.ResourceResponse;
import com.example.model.entity.Restaurant;
import com.example.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersistentRestaurantManagementController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/v2/restaurants")
    public ResponseEntity<ResourceResponse> createRestaurant(@RequestBody Restaurant restaurant) {
        ResourceResponse resp = new ResourceResponse();
        if (restaurant == null || restaurant.getName() == null || restaurant.getName().isEmpty()) {
            resp.setMessage("Invalid Input");
            return ResponseEntity.badRequest().body(resp);
        }

        Integer id = restaurantService.createRestaurant(restaurant);
        resp.setResource("/v2/restaurants/" + id);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/v2/restaurants/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Integer id) {
        if (id == null || id < 0) {
            return ResponseEntity.badRequest().build();
        }
        Restaurant restaurant = restaurantService.getRestaurant(id);
        if (restaurant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping("/v2/restaurants")
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        List<Restaurant> allRestaurants = restaurantService.getRestaurants();
        return ResponseEntity.ok(allRestaurants);
    }



    @DeleteMapping("/v2/restaurants/{id}")
    public ResponseEntity<ResourceResponse> deleteRestaurant(@PathVariable Integer id) {
        ResourceResponse resp = new ResourceResponse();
        if (id == null || id < 0) {
            resp.setMessage("id is invalid");
            return ResponseEntity.badRequest().body(resp);
        }
        boolean deleted = restaurantService.deleteRestaurant(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}