package com.harshchoudhary.projects.AirBnb_SpringBoot.service;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.BookingDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.BookingRequestDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.GuestDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface IBookingService {
   BookingDTO initialiseBooking(BookingRequestDTO bookingRequestDTO);

   BookingDTO addGuest(List<GuestDTO> guestDTOList, Long bookingId);

   void deleteAllBookingsByRoom(Long roomId);
}
