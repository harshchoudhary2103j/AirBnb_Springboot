package com.harshchoudhary.projects.AirBnb_SpringBoot.service;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.RoomDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Room;

import java.util.List;

public interface IRoomService {
    RoomDTO createNewRoom(Long hotelId,RoomDTO roomDTO);
    List<RoomDTO> getAllRoomsInHotel(Long hotelId);
    RoomDTO getRoomById(Long roomId);
    void deleteRoomById(Long roomId);
}
