package com.tekion.User.service.Service;

import Model.Order;
import Model.User;
import com.tekion.User.service.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String addUser(User user){
        List<Order> orders = new ArrayList<>();
        user.setOrders(orders);
        userRepository.save(user);
        return "success";
    }
    public User getUserById(int id){
        User user = userRepository.findById(id).orElse(null);
        return user;
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public String addUserOrder(int userId, Order order){
        User user =userRepository.findById(userId).orElse(null);
        if(user==null)
            return null;
        List<Order> orders=user.getOrders();
        orders.add(order);
        user.setOrders(orders);
        userRepository.save(user);
        return "success";
    }
}
