package com.harshchoudhary.projects.AirBnb_SpringBoot.dto;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.User;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class GuestDTO {
    private Long id;
    private User user;
    private String name;
    private Gender gender;
}
