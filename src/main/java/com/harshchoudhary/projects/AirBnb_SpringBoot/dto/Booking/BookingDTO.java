package com.harshchoudhary.projects.AirBnb_SpringBoot.dto.Booking;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.User.GuestDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Users.User;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.enums.BookingStatus;
import lombok.Data;

import java.math.BigDecimal;
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
    private BigDecimal amount;


}
