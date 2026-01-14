package com.harshchoudhary.projects.AirBnb_SpringBoot.service;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.RoomDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Hotel;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Room;
import com.harshchoudhary.projects.AirBnb_SpringBoot.exception.ResourceNotFoundException;
import com.harshchoudhary.projects.AirBnb_SpringBoot.repository.HotelRepository;
import com.harshchoudhary.projects.AirBnb_SpringBoot.repository.InventoryRepository;
import com.harshchoudhary.projects.AirBnb_SpringBoot.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CRoomService implements IRoomService{
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final InventoryRepository inventoryRepository;
    private  final IInventoryService iInventoryService;
    @Override
    public RoomDTO createNewRoom(Long hotelId,RoomDTO roomDTO) {
        log.info("Creating a new Room in Hotel with HotelId: "+hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with HotelId: "+hotelId));

        Room room = modelMapper.map(roomDTO,Room.class);
        room.setHotel(hotel);
        hotel.getRooms().add(room);

        room = roomRepository.save(room);
        // Create Inventory as soon as room is created and hotel is active
        if(hotel.isActive()){
            iInventoryService.initializeRoomForAYear(room);
        }

        return modelMapper.map(room, RoomDTO.class);
    }

    @Override
    public List<RoomDTO> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all  Rooms in Hotel with HotelId: "+hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with HotelId: "+hotelId));

        return hotel.getRooms()
                .stream()
                .map((element)->modelMapper.map(element, RoomDTO.class)).collect(Collectors.toUnmodifiableList());


    }

    @Override
    public RoomDTO getRoomById(Long roomId) {
        log.info("Getting all  Rooms with RoomId: "+roomId);
        Room room = roomRepository.findById(roomId)
                .orElseThrow(()->new ResourceNotFoundException("Room not found with RoomId: "+roomId));

        return modelMapper.map(room,RoomDTO.class);
    }
    @Transactional
    @Override
    public void deleteRoomById(Long roomId) {
        log.info("Deleting the room with the room Id: "+roomId);
        Room room = roomRepository.findById(roomId)
                .orElseThrow(()->new ResourceNotFoundException("Room not found with RoomId: "+roomId));


        // DELETE ALL THE FUTURE INVENTORY


        iInventoryService.deleteAllInventories(room);


        room.getHotel().getRooms().remove(room);


        roomRepository.deleteById(roomId);

    }
}
