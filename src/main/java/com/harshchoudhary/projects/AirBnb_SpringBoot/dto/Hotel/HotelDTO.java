package com.harshchoudhary.projects.AirBnb_SpringBoot.dto.Hotel;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Hotel.HotelContactInfo;
import lombok.Data;

@Data
public class HotelDTO {
    private Long id;
    private String name;
    private String city;
    private String[]photos;
    private String[] amenities;
    private HotelContactInfo hotelContactInfo;
    private boolean isActive;
}
