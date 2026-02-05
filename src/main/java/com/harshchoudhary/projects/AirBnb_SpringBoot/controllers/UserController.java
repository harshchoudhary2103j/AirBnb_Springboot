package com.harshchoudhary.projects.AirBnb_SpringBoot.controllers;


import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.BookingDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.GuestDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.UserDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.UserProfileUpdateReqDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.service.IBookingService;
import com.harshchoudhary.projects.AirBnb_SpringBoot.service.IGuestService;
import com.harshchoudhary.projects.AirBnb_SpringBoot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final IBookingService bookingService;
    private final IGuestService guestService;

    @PatchMapping("/profile")
    public ResponseEntity<Void>updateProfile(@RequestBody UserProfileUpdateReqDTO updateReqDTO){
        userService.updateProfile(updateReqDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/myBookings")
    public ResponseEntity<List<BookingDTO>>getMyBookings(){
        return ResponseEntity.ok(bookingService.getMyBookings());

    }
    @GetMapping("/profile")
    public ResponseEntity<UserDTO>getMyProfile(){
        return ResponseEntity.ok(userService.getMyProfile());
    }

    @GetMapping("/guests")
    public ResponseEntity<List<GuestDTO>> getAllGuests() {
        return ResponseEntity.ok(guestService.getAllGuests());
    }
    @PostMapping("/guests")
    public ResponseEntity<GuestDTO> addNewGuest(@RequestBody GuestDTO guestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(guestService.addNewGuest(guestDto));
    }
    @PutMapping("guests/{guestId}")
    public ResponseEntity<Void> updateGuest(@PathVariable Long guestId, @RequestBody GuestDTO guestDto) {
        guestService.updateGuest(guestId, guestDto);
        return ResponseEntity.noContent().build();

    }
    @DeleteMapping("guests/{guestId}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long guestId) {
        guestService.deleteGuest(guestId);
        return ResponseEntity.noContent().build();
    }


}
