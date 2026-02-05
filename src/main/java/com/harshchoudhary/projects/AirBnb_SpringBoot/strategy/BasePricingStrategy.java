package com.harshchoudhary.projects.AirBnb_SpringBoot.strategy;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Inventories.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;


@RequiredArgsConstructor
public class BasePricingStrategy implements PricingStrategy{
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        return inventory.getRoom().getBasePrice();
    }
}
