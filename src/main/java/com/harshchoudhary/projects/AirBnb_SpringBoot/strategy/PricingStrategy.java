package com.harshchoudhary.projects.AirBnb_SpringBoot.strategy;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Inventories.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Inventory inventory);
}
