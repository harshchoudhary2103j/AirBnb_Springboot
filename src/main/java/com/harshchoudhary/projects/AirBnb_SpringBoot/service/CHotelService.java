package com.harshchoudhary.projects.AirBnb_SpringBoot.service;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.HotelDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Hotel;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Room;
import com.harshchoudhary.projects.AirBnb_SpringBoot.exception.ResourceNotFoundException;
import com.harshchoudhary.projects.AirBnb_SpringBoot.repository.HotelRepository;
import com.harshchoudhary.projects.AirBnb_SpringBoot.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CHotelService implements IHotelService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final IInventoryService iInventoryService;

    public CHotelService(HotelRepository hotelRepository, ModelMapper modelMapper, IInventoryService iInventoryService,
                         RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
        this.iInventoryService = iInventoryService;
        this.roomRepository = roomRepository;
    }

    @Override
    public HotelDTO createNewHotel(HotelDTO hotel) {
        log.info("Creating a new hotel with name: "+ hotel.getName());
        Hotel hotel1 = modelMapper.map(hotel,Hotel.class);
        hotel1.setActive(false);
        Hotel savedHotel = hotelRepository.save(hotel1);
        log.info("Created a new Hotel with Id: "+savedHotel.getId());
        return modelMapper.map(savedHotel,HotelDTO.class);



    }

    @Override
    public HotelDTO getHotelById(Long id) {
        log.info("Getting the hotel with Id: " + id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id: " + id));
        return modelMapper.map(hotel, HotelDTO.class);

    }

    @Override
    public HotelDTO updateHotelById(Long id,HotelDTO hotelDTO) {
        log.info("Updating the hotel with Id: " + id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id: " + id));
        modelMapper.map(hotelDTO,hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel,HotelDTO.class);
    }
    @Transactional
    @Override
    public void deleteHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Hotel not found with Id: " + id));


        // Delete the future inventories of the hotel
        for(Room room:hotel.getRooms()){
            iInventoryService.deleteAllInventories(room);
            roomRepository.deleteById(room.getId());
        }
        hotelRepository.deleteById(id);


    }
    @Transactional
    @Override
    public void activateHotel(Long hotelId) {
        log.info("Updating the hotel with Id: " + hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id: " + hotelId));
        hotel.setActive(true);
        // Create inventory for all rooms of this hotel
        for(Room room:hotel.getRooms()){
            iInventoryService.initializeRoomForAYear(room);
        }


    }
}
