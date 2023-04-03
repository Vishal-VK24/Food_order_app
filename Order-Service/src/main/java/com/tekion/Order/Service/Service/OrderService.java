package com.tekion.Order.Service.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekion.Order.Service.Dto.OrderRequest;
import Model.Item;
import Model.Order;
import com.tekion.Order.Service.Kafka.JsonKafkaProducer;
import com.tekion.Order.Service.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final JsonKafkaProducer kafkaProducer;
    private final ItemClient itemClient;

    public String addOrder(OrderRequest orderRequest){
        Order order= new Order();
        order.setId(orderRequest.getId());
        order.setUserId(orderRequest.getUserId());
        order.setRestaurant(orderRequest.getRestaurantName());
        List<Item> items = itemClient.getAllItemsPrivate();
        System.out.println(itemClient.toString());
//        List<Item> items=webClientBuilder.build().get()
//                .uri("http://item-service/api/item/private/items")
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<List<Item>>() {})
//               .block();
        List<Item> result=new ArrayList<>();
        System.out.println(items);
        for(Item i:items){
            if(orderRequest.getItemsIds().contains(i.getId()))
                result.add(i);
        }
        order.setItems(result);
        orderRepository.save(order);
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr="";
        try {
            // Converting the Java object into a JSON string
             jsonStr= Obj.writeValueAsString(order);
            // Displaying Java object into a JSON string
            System.out.println(jsonStr);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(jsonStr);
        kafkaProducer.sendMessage(order);
        return "success";
    }
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }
    public Order getOrderById(int id){
        return orderRepository.findById(id).orElse(null);
    }
}
