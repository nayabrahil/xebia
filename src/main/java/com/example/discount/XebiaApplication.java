package com.example.discount;

import com.example.discount.common.AmountFormatter;
import com.example.discount.common.ItemType;
import com.example.discount.common.UserType;
import com.example.discount.domain.cart.ShoppingCart;
import com.example.discount.entity.Item;
import com.example.discount.entity.Subscription;
import com.example.discount.entity.User;
import com.example.discount.service.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

@SpringBootApplication
public class XebiaApplication implements CommandLineRunner {

    @Autowired
    DiscountStrategy discountStrategy;

    public static void main(String[] args) {
        SpringApplication.run(XebiaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //For Employee
        case1();
        // For Affiliate
        case2();
        // For Subscription
        case3();
        // For Every Hundred
        case4();
    }

    //Creating test cases separately will take alot of time. Hence, writing them here only
    void case1(){
        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item();
        item1.setItemId(1);
        item1.setName("MUESLI");
        item1.setType(ItemType.GROCERIES);
        item1.setPrice(new BigDecimal(87));

        Item item2 = new Item();
        item2.setItemId(1);
        item2.setName("MUESLI");
        item2.setType(ItemType.GROCERIES);
        item2.setPrice(new BigDecimal(87));


        Item item3 = new Item();
        item3.setItemId(1);
        item3.setName("FRIDGE");
        item3.setType(ItemType.ELECTRONICS);
        item3.setPrice(new BigDecimal(87));


        cart.updateCart(cart, item1);
        cart.updateCart(cart, item2);
        cart.updateCart(cart, item3);

        Subscription subscription = new Subscription();
        subscription.setStartDate(LocalDate.of(2004, 4, 2));



        User user = new User(1, "Nayab Rahil", UserType.EMPLOYEE, cart, subscription);

        discountStrategy.applyDiscount(user);

        System.out.println(discountStrategy.getDiscountValue(user.getShoppingCart()));
    }
    void case2(){
        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item();
        item1.setItemId(1);
        item1.setName("MUESLI");
        item1.setType(ItemType.GROCERIES);
        item1.setPrice(new BigDecimal(87));

        Item item2 = new Item();
        item2.setItemId(1);
        item2.setName("MUESLI");
        item2.setType(ItemType.ELECTRONICS);
        item2.setPrice(new BigDecimal(87));


        Item item3 = new Item();
        item3.setItemId(1);
        item3.setName("FRIDGE");
        item3.setType(ItemType.ELECTRONICS);
        item3.setPrice(new BigDecimal(87));


        cart.updateCart(cart, item1);
        cart.updateCart(cart, item2);
        cart.updateCart(cart, item3);

        Subscription subscription = new Subscription();
        subscription.setStartDate(LocalDate.of(2004, 4, 2));



        User user = new User(1, "Nayab Rahil", UserType.AFFILIATE, cart, subscription);

        discountStrategy.applyDiscount(user);

        System.out.println(discountStrategy.getDiscountValue(user.getShoppingCart()));
    }

    void case3(){
        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item();
        item1.setItemId(1);
        item1.setName("MUESLI");
        item1.setType(ItemType.ELECTRONICS);
        item1.setPrice(new BigDecimal(87));

        Item item2 = new Item();
        item2.setItemId(1);
        item2.setName("MUESLI");
        item2.setType(ItemType.ELECTRONICS);
        item2.setPrice(new BigDecimal(100));


        Item item3 = new Item();
        item3.setItemId(1);
        item3.setName("FRIDGE");
        item3.setType(ItemType.ELECTRONICS);
        item3.setPrice(new BigDecimal(900));


        cart.updateCart(cart, item1);
        cart.updateCart(cart, item2);
        cart.updateCart(cart, item3);

        Subscription subscription = new Subscription();
        subscription.setStartDate(LocalDate.of(2004, 4, 2));

        User user = new User(1, "Nayab Rahil", UserType.OTHER, cart, subscription);

        discountStrategy.applyDiscount(user);

        System.out.println(discountStrategy.getDiscountValue(user.getShoppingCart()));
    }

    void case4(){
        ShoppingCart cart = new ShoppingCart();
        cart.setItems(new HashMap<>());

        Item item1 = new Item();
        item1.setItemId(1);
        item1.setName("MUESLI");
        item1.setType(ItemType.ELECTRONICS);
        item1.setPrice(new BigDecimal(80));

        Item item2 = new Item();
        item2.setItemId(1);
        item2.setName("MUESLI");
        item2.setType(ItemType.ELECTRONICS);
        item2.setPrice(new BigDecimal(11));


        Item item3 = new Item();
        item3.setItemId(1);
        item3.setName("FRIDGE");
        item3.setType(ItemType.ELECTRONICS);
        item3.setPrice(new BigDecimal(9));


        cart.updateCart(cart, item1);
        cart.updateCart(cart, item2);
        cart.updateCart(cart, item3);

        Subscription subscription = new Subscription();
        subscription.setStartDate(LocalDate.of(2018, 4, 2));

        User user = new User(1, "Nayab Rahil", UserType.OTHER, cart, subscription);

        discountStrategy.applyDiscount(user);

        System.out.println(discountStrategy.getDiscountValue(user.getShoppingCart()));
    }
}
