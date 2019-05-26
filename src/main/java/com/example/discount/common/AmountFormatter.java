package com.example.discount.common;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class AmountFormatter {
    public static String formatRupees(BigDecimal value) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_EVEN);
        return format.format(value);
    }
}
