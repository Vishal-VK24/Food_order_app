package com.tekion.Item.Service.Repository;

import com.tekion.Item.Service.Model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item,Integer> {
    @Query("{'itemDesc':?0}")
    List<Item> findItemsByDesc(String Desc);
    @Query("{'itemDesc': 'chinese'}")
    Page<Item> findItemsByPage(Pageable pageable);
}
