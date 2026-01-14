package com.harshchoudhary.projects.AirBnb_SpringBoot.dto;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.HotelContactInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
