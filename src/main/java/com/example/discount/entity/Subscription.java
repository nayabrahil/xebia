package com.example.discount.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Subscription {
    LocalDate startDate;
    LocalDate endDate;
}
