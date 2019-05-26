package com.example.discount.entity;

import com.example.discount.common.ItemType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {

    private long itemId;
    private String name;
    private ItemType type;
    private BigDecimal price;
    private Attributes attributes;
}