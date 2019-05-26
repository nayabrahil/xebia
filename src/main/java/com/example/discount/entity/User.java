package com.example.discount.entity;

import com.example.discount.common.UserType;
import com.example.discount.domain.cart.Cart;
import com.example.discount.service.DiscountStrategy;
import lombok.Value;

@Value
public final class User implements AbstractUser {

    private final long userId;
    private final String name;
    private final UserType userType;
    private final Cart shoppingCart;
    private final Subscription subscription;

    @Override
    public void accept(DiscountStrategy discountStrategy) {
        discountStrategy.applyDiscount(this);
    }
}
