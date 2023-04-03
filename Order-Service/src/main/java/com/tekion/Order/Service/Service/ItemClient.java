package com.tekion.Order.Service.Service;

import Model.Item;
import org.apache.kafka.clients.admin.AdminClientConfig;
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


