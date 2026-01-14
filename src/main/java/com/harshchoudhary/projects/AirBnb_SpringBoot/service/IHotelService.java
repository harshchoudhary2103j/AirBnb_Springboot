package com.harshchoudhary.projects.AirBnb_SpringBoot.service;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.HotelDTO;

public interface IHotelService {
    HotelDTO createNewHotel(HotelDTO hotel);
    HotelDTO getHotelById(Long id);
    HotelDTO updateHotelById(Long id,HotelDTO hotelDTO);
    void deleteHotelById(Long id);
    void activateHotel(Long hotelId);
}
