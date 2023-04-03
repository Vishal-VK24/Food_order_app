package com.tekion.Order.Service.Controller;

import com.tekion.Order.Service.Dto.OrderRequest;
import Model.Order;
import com.tekion.Order.Service.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping
    public List<Order> getAllOrders(){
        logger.info("Requesting all order details");
        return orderService.getAllOrder();
    }
    @PostMapping
    public String addOrder(@RequestBody OrderRequest orderRequest){
        logger.info("Order is placed");
        return orderService.addOrder(orderRequest);
    }
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id){
        logger.info("Requesting order: "+id);
        return orderService.getOrderById(id);
    }
}
