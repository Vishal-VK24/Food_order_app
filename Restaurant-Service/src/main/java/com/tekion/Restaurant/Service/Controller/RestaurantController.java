package com.tekion.Restaurant.Service.Controller;

import com.tekion.Restaurant.Service.Model.ItemRequest;
import com.tekion.Restaurant.Service.Model.Restaurant;
import com.tekion.Restaurant.Service.Service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/res")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        List<Restaurant> restaurants= restaurantService.getAllRestaurants();
        if(restaurants==null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable int id){
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        if(restaurant==null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }
    @PostMapping
    public ResponseEntity<String> addRestaurant(@RequestBody Restaurant restaurant){
        return ResponseEntity.ok().body(restaurantService.addRestaurant(restaurant));
    }
    @PostMapping("/items/{id}")
    public ResponseEntity<Restaurant> addItemsInRestaurant(@PathVariable int id,@RequestBody ItemRequest  item){
        Restaurant restaurant=restaurantService.addItemsInRestaurant(item.getItemIds(),id);
        return ResponseEntity.ok().body(restaurant);
    }
    @GetMapping("/private")
    public Restaurant getRestaurantByIdForOrder(@RequestParam int id){
        return restaurantService.getRestaurantById(id);
    }
}
