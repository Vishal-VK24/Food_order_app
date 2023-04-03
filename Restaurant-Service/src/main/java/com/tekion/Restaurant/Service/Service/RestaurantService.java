package com.tekion.Restaurant.Service.Service;

import com.tekion.Restaurant.Service.Model.Item;
import com.tekion.Restaurant.Service.Model.Restaurant;
import com.tekion.Restaurant.Service.Repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final WebClient.Builder webClientBuilder;
    private final ItemClient itemClient;
    public String addRestaurant(@RequestBody Restaurant restaurant){
        restaurantRepository.save(restaurant);
        return "success";
    }
    public List<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll();
    }
    public Restaurant getRestaurantById(int Id){
        return restaurantRepository.findById(Id).orElse(null);
    }
    public Restaurant addItemsInRestaurant(List<Integer> itemIds,int id){
        Restaurant restaurant = getRestaurantById(id);
        if(restaurant==null)
            return null;
        List<Item> items = itemClient.getAllItemsPrivate();
//        List<Item> items=webClientBuilder.build().get()
//                .uri("http://localhost:8081/api/item/private/items")
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<List<Item>>() {})
//                .block();
        List<Item> result=new ArrayList<>();
        System.out.println(items);
        for(Item i:items){
            if(itemIds.contains(i.getId()))
                result.add(i);
        }
        restaurant.setItems(result);
        restaurantRepository.save(restaurant);
        return restaurant;
    }
}
