package com.example.discount.entity;

import com.example.discount.service.DiscountStrategy;

public interface AbstractUser {
    void accept(DiscountStrategy discountStrategy);
}
