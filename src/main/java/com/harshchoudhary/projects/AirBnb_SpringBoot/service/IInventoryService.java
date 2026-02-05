package com.harshchoudhary.projects.AirBnb_SpringBoot.service;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.*;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Room;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IInventoryService {
    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);


    Page<HotelPriceDTO> searchHotels(HotelSearchRequest hotelSearchRequest);

    @Nullable List<InventoryDTO> getAllInventoryByRoom(Long roomId);

    void updateInventory(Long roomId, UpdateInventoryRequestDTO inventoryRequestDTO);
}
