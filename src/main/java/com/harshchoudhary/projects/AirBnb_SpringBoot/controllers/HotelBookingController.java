package com.harshchoudhary.projects.AirBnb_SpringBoot.controllers;


import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.BookingDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.BookingRequestDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.GuestDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Booking;
import com.harshchoudhary.projects.AirBnb_SpringBoot.service.IBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class HotelBookingController {
    private final IBookingService bookingService;

    public HotelBookingController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/init")
    public ResponseEntity<BookingDTO>initialiseBooking(@RequestBody BookingRequestDTO bookingRequestDTO){
        return ResponseEntity.ok(bookingService.initialiseBooking(bookingRequestDTO));
    }
    @PostMapping("/{bookingId}/addGuests")
    public ResponseEntity<BookingDTO>addGuests(@RequestBody List<GuestDTO> guestDTOList, @PathVariable Long bookingId){
        return ResponseEntity.ok(bookingService.addGuest(guestDTOList,bookingId));

    }
}
