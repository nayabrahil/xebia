package com.example.discount.common;

import java.math.BigDecimal;

public class CommonUtil {

    public static BigDecimal getBigDecimal(String number){
        BigDecimal decimal = new BigDecimal(number);
        decimal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return decimal;
    }
}
