package com.example.discount.domain.cart;

import com.example.discount.entity.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCart extends Cart {

    @Override
    public boolean updateCart(Cart cart, Item item) {
        Integer existingCountOfItem = this.getItems().get(item);
        if(existingCountOfItem!=null && existingCountOfItem>0) {
            this.getItems().merge(item, 1, Integer::sum);
        }
        else{
            this.getItems().put(item, 1);
        }
        cart.setTotalCost(this.totalCost.add(item.getPrice()));
        return true;
    }

}
