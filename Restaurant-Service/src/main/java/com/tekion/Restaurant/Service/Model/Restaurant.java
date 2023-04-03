package com.tekion.Restaurant.Service.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Restaurant {
    @Id
    private int id;
    private String restaurantName;
    private String restaurantLocation;
    private List<String> restaurantCuisine;
    private List<Item> items;
    private String restaurantEmail;
}
