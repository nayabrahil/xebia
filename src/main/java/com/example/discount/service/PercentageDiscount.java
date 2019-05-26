package com.example.discount.service;

import com.example.discount.common.AmountFormatter;
import com.example.discount.common.Constants;
import com.example.discount.common.ItemType;
import com.example.discount.domain.cart.Cart;
import com.example.discount.entity.Subscription;
import com.example.discount.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.example.discount.common.CommonUtil.getBigDecimal;

@Service
public class PercentageDiscount implements DiscountStrategy {

    private boolean toBeApplied = false;

    @Override
    public void applyDiscount(User user) {
        checkAndApplyDiscountForUserType(user);
        if (!toBeApplied) {
            checkAndApplyOtherDiscount(user);
        }
        toBeApplied=false;
    }

    private void checkAndApplyDiscountForUserType(User user) {
        BigDecimal amountAfterSkippedItem = getAmountAfterSkippedItem(user.getShoppingCart());
        Integer discountPercentage = getDiscountForUser(user.getUserType());
        BigDecimal discount = amountAfterSkippedItem.multiply(getBigDecimal(discountPercentage.toString()))
                .divide(getBigDecimal("100"));

        if (discount.compareTo(BigDecimal.ZERO) > 0) {
            this.toBeApplied = true;
            updateShoppingCartAmountPayable(user.getShoppingCart(), discount);
        }
    }

    private void checkAndApplyOtherDiscount(final User user) {
        Integer discountPercentage = getDiscountPercentageForSubscription(user.getSubscription(), 2);
        BigDecimal amountAfterSkippedItem = getAmountAfterSkippedItem(user.getShoppingCart());

        BigDecimal discount;

        if (!toBeApplied) {
            discount = this.getDiscountForEveryHundred(user.getShoppingCart().getTotalCost());
        } else {
            discount = amountAfterSkippedItem.multiply(
                    getBigDecimal(discountPercentage.toString()))
                    .divide(getBigDecimal(Constants.DIGIT_HUNDRED));
        }

        updateShoppingCartAmountPayable(user.getShoppingCart(), discount);
    }

    private BigDecimal getAmountAfterSkippedItem(Cart shoppingCart) {
        return getPriceForDiscountAfterSkipItemType(
                shoppingCart.getItems(),
                ItemType.GROCERIES);
    }

    private int getDiscountPercentageForSubscription(final Subscription subscription, final int range) {
        long membershipInYear = ChronoUnit.YEARS.between(subscription.getStartDate(), LocalDate.now());
        if (membershipInYear >= range) {
            this.toBeApplied = true;
            return 5;
        }
        return 0;
    }

    private BigDecimal getDiscountForEveryHundred(final BigDecimal totalCost) {
        if (totalCost.compareTo(BigDecimal.valueOf(100)) < 0) {
            return getBigDecimal(Constants.DIGIT_ZERO);
        }
        return (totalCost.divideToIntegralValue(new BigDecimal(100))).multiply(new BigDecimal(Constants.DIGIT_FIVE));
    }

    @Override
    public String getDiscountValue(final Cart cart) {
        return AmountFormatter.formatRupees(cart.getTotalCost().subtract(cart.getAmountPayable()));
    }

    private void updateShoppingCartAmountPayable(final Cart shoppingCart, final BigDecimal discount) {
        shoppingCart.setAmountPayable(
                shoppingCart.getTotalCost()
                        .subtract(discount)
        );
    }

}
