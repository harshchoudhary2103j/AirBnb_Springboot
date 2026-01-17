package com.harshchoudhary.projects.AirBnb_SpringBoot.service;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.HotelDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.HotelSearchRequest;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.RoomDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Room;
import org.springframework.data.domain.Page;

public interface IInventoryService {
    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);


    Page<HotelDTO> searchHotels(HotelSearchRequest hotelSearchRequest);
}
