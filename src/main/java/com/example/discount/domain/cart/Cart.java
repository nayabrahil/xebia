package com.example.discount.domain.cart;

import com.example.discount.entity.Item;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public abstract class Cart {

    protected Map<Item, Integer> items = new HashMap<>();

    protected BigDecimal totalCost=BigDecimal.ZERO;
    protected BigDecimal amountPayable=BigDecimal.ZERO;

    Cart(){
        totalCost.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        amountPayable.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    abstract boolean updateCart(Cart cart, final Item item);


}
