package com.harshchoudhary.projects.AirBnb_SpringBoot.service;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.RoomDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Room;

public interface IInventoryService {
    void initializeRoomForAYear(Room room);
    void deleteFutureInventories(Room room);
    void deleteAllInventories(Room room);

}
