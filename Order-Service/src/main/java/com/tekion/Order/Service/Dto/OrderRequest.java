package com.tekion.Order.Service.Dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private int id;
    private String restaurantName;
    private List<Integer> itemsIds;
    private int userId;
}
