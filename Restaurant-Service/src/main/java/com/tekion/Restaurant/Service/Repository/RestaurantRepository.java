package com.tekion.Restaurant.Service.Repository;

import com.tekion.Restaurant.Service.Model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant,Integer> {
}
