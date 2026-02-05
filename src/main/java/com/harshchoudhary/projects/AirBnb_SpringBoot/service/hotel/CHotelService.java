package com.harshchoudhary.projects.AirBnb_SpringBoot.service.hotel;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.Hotel.HotelDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.Hotel.HotelInfoDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.Room.RoomDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Hotel.Hotel;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Rooms.Room;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Users.User;
import com.harshchoudhary.projects.AirBnb_SpringBoot.exception.ResourceNotFoundException;
import com.harshchoudhary.projects.AirBnb_SpringBoot.exception.UnauthorizedException;
import com.harshchoudhary.projects.AirBnb_SpringBoot.repository.HotelRepository;
import com.harshchoudhary.projects.AirBnb_SpringBoot.repository.RoomRepository;
import com.harshchoudhary.projects.AirBnb_SpringBoot.service.bookingsAndPayments.IBookingService;
import com.harshchoudhary.projects.AirBnb_SpringBoot.service.inventory.IInventoryService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.harshchoudhary.projects.AirBnb_SpringBoot.util.Apputils.getCurrentUser;

@Service
@Slf4j

public class CHotelService implements IHotelService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final IInventoryService iInventoryService;
    private final IBookingService iBookingService;

    public CHotelService(HotelRepository hotelRepository, ModelMapper modelMapper, IInventoryService iInventoryService,
                         RoomRepository roomRepository, IBookingService iBookingService) {
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
        this.iInventoryService = iInventoryService;
        this.roomRepository = roomRepository;
        this.iBookingService = iBookingService;
    }

    @Override
    public HotelDTO createNewHotel(HotelDTO hotel) {
        log.info("Creating a new hotel with name: "+ hotel.getName());
        Hotel hotel1 = modelMapper.map(hotel,Hotel.class);
        hotel1.setActive(false);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        hotel1.setOwner(user);
        Hotel savedHotel = hotelRepository.save(hotel1);
        log.info("Created a new Hotel with Id: "+savedHotel.getId());
        return modelMapper.map(savedHotel,HotelDTO.class);



    }

    @Override
    public HotelDTO getHotelById(Long id) {
        log.info("Getting the hotel with Id: " + id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id: " + id));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw new UnauthorizedException("This hotel is not owned by the user with id: "+user.getId());
        }
        return modelMapper.map(hotel, HotelDTO.class);

    }

    @Override
    public HotelDTO updateHotelById(Long id,HotelDTO hotelDTO) {
        log.info("Updating the hotel with Id: " + id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id: " + id));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw new UnauthorizedException("This hotel is not owned by the user with id: "+user.getId());
        }
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw new UnauthorizedException("This hotel is not owned by the user with id: "+user.getId());
        }


        // Delete the future inventories of the hotel
        for(Room room:hotel.getRooms()){
            iBookingService.deleteAllBookingsByRoom(room.getId());
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw new UnauthorizedException("This hotel is not owned by the user with id: "+user.getId());
        }

        hotel.setActive(true);
        // Create inventory for all rooms of this hotel
        for(Room room:hotel.getRooms()){
            iInventoryService.initializeRoomForAYear(room);
        }


    }

    @Override
    public HotelInfoDTO getInfobyHotelId(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with Id: "+hotelId));
        List<RoomDTO>roomDTOList = hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDTO.class)).collect(Collectors.toList());
        return new HotelInfoDTO(modelMapper.map(hotel, HotelDTO.class),roomDTOList);

    }

    @Override
    public List<HotelDTO> getAllHotel() {
        User user = getCurrentUser();
        List<Hotel> hotelList = hotelRepository.findByOwner(user);
        return hotelList.stream().map((element) -> modelMapper.map(element, HotelDTO.class)).collect(Collectors.toList());
    }
}
