package com.tekion.Item.Service.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Item {
    @Id
    private int id;
    private String itemName;
    private String itemDesc;
    private double itemPrice;
}
