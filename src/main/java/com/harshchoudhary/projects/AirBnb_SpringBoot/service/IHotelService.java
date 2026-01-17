package com.harshchoudhary.projects.AirBnb_SpringBoot.service;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.HotelDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.HotelInfoDTO;
import org.jspecify.annotations.Nullable;

public interface IHotelService {
    HotelDTO createNewHotel(HotelDTO hotel);
    HotelDTO getHotelById(Long id);
    HotelDTO updateHotelById(Long id,HotelDTO hotelDTO);
    void deleteHotelById(Long id);
    void activateHotel(Long hotelId);

    HotelInfoDTO getInfobyHotelId(Long hotelId);
}
