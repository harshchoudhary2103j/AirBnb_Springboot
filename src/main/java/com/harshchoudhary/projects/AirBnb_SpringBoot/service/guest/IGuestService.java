package com.harshchoudhary.projects.AirBnb_SpringBoot.service.guest;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.User.GuestDTO;

import java.util.List;

public interface IGuestService {
     List<GuestDTO> getAllGuests();

     GuestDTO addNewGuest(GuestDTO guestDto);

    void updateGuest(Long guestId, GuestDTO guestDto);

    void deleteGuest(Long guestId);
}
