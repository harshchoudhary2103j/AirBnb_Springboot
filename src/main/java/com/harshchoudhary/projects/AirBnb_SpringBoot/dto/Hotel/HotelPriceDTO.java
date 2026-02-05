package com.harshchoudhary.projects.AirBnb_SpringBoot.dto.Hotel;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Hotel.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelPriceDTO {
    private Hotel hotel;
    private Double price;
}
