package com.tekion.Restaurant.Service.Service;

import com.tekion.Restaurant.Service.Model.Item;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "item-service")
public interface ItemClient {
    @GetMapping("/api/item/private/items")
    @LoadBalanced
    public List<Item> getAllItemsPrivate();
}
