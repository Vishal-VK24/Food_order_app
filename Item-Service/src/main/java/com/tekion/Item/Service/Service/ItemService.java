package com.tekion.Item.Service.Service;

import com.tekion.Item.Service.Model.Item;
import com.tekion.Item.Service.Repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    public String addItem(@RequestBody Item item){
        itemRepository.save(item);
        return "success";
    }
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }
    public Item getItemById(int Id){
        return itemRepository.findById(Id).orElse(null);
    }
    public List<Item> getItemsByIds(List<Integer> ids){
        Iterable<Item> items=itemRepository.findAllById(ids);
        List<Item> result= new ArrayList<>();
        for (Item str : items) {
            result.add(str);
        }
        return result;
    }
    public List<Item> getItemsByDesc(String Desc){
        return itemRepository.findItemsByDesc(Desc);
    }
    public Page<Item> getItemsByPage(Pageable pageable){
        return itemRepository.findItemsByPage(pageable);
    }
}
