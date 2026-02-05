package com.harshchoudhary.projects.AirBnb_SpringBoot.service;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.RoomDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Room;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface IRoomService {
    RoomDTO createNewRoom(Long hotelId,RoomDTO roomDTO);
    List<RoomDTO> getAllRoomsInHotel(Long hotelId);
    RoomDTO getRoomById(Long roomId);
    void deleteRoomById(Long roomId);

   RoomDTO updateRoomById(Long hotelId, Long roomId, RoomDTO roomDto);
}
