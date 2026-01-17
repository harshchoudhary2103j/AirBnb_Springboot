package com.harshchoudhary.projects.AirBnb_SpringBoot.dto;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Guest;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Hotel;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Room;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.User;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
@Data
public class BookingDTO {
    private Long id;
    private User user;
    private Integer roomsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus bookingStatus;
    private Set<GuestDTO> guests;


}
