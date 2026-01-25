package com.harshchoudhary.projects.AirBnb_SpringBoot.strategy;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@RequiredArgsConstructor
public class SurgePricingStrategy implements PricingStrategy{
    private final PricingStrategy wrapped;
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        return wrapped.calculatePrice(inventory)
                .multiply(inventory.getSurgeFactor());
    }
}
