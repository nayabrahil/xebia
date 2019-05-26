package com.example.discount.service;

import com.example.discount.common.CommonUtil;
import com.example.discount.common.ItemType;
import com.example.discount.common.UserType;
import com.example.discount.domain.cart.Cart;
import com.example.discount.entity.Item;
import com.example.discount.entity.User;

import java.math.BigDecimal;
import java.util.Map;

public interface DiscountStrategy {

    void applyDiscount(User user);

    default public int getDiscountForUser(UserType userType){
        int discountPercentage = 0;
        switch (userType){
            case EMPLOYEE : discountPercentage = 30;
                            break;
            case AFFILIATE: discountPercentage = 10;
                            break;
            default: break;
        }
        return discountPercentage;
    }

    default public BigDecimal getPriceForDiscountAfterSkipItemType(Map<Item, Integer> itemMap, ItemType itemType){
        BigDecimal priceForDiscount = CommonUtil.getBigDecimal("0");
        for(Map.Entry<Item, Integer> items : itemMap.entrySet()){
            if(!items.getKey().getType().equals(itemType)){
                priceForDiscount = priceForDiscount.add(CommonUtil.getBigDecimal(items.getValue().toString()).multiply(items.getKey().getPrice()));
            }
        }
        return priceForDiscount;
    }

    String getDiscountValue(Cart cart);

}
