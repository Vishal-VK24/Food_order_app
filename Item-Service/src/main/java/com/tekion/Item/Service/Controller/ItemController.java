package com.tekion.Item.Service.Controller;

import com.tekion.Item.Service.Model.Item;
import com.tekion.Item.Service.Model.ItemRequest;
import com.tekion.Item.Service.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {
    private final ItemService itemService;
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems(){
        List<Item> Items= itemService.getAllItems();
        if(Items==null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(Items);
    }
    @GetMapping("{id}")
    public ResponseEntity<Item> getItemById(@PathVariable int id){
        Item item = itemService.getItemById(id);
        if(item==null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(item);
    }
    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody Item restaurant){
        return ResponseEntity.ok().body(itemService.addItem(restaurant));
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Item>> getItemsByDesc(@RequestParam String Desc){
        List<Item> items=itemService.getItemsByDesc(Desc);
        if(items==null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(items);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<Item>> getItemsByPage(){
        Pageable pageable = PageRequest.of(0,3, Sort.by("itemName").ascending());
        Page<Item> items = itemService.getItemsByPage(pageable);
        return ResponseEntity.ok().body(items);
    }
    @GetMapping("/ids")
    public List<Item> getItemsFromIds(@RequestParam List<Integer> ids){
        return itemService.getItemsByIds(ids);
    }
    @GetMapping("/private/items")
    public List<Item> getAllItemsPrivate(){
        return itemService.getAllItems();
    }
}
