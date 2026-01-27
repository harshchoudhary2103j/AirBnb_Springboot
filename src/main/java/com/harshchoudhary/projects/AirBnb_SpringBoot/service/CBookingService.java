package com.harshchoudhary.projects.AirBnb_SpringBoot.service;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.BookingDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.BookingRequestDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.GuestDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.*;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.enums.BookingStatus;
import com.harshchoudhary.projects.AirBnb_SpringBoot.exception.ResourceNotFoundException;
import com.harshchoudhary.projects.AirBnb_SpringBoot.exception.UnauthorizedException;
import com.harshchoudhary.projects.AirBnb_SpringBoot.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CBookingService implements IBookingService{
    private final GuestRepository guestRepository;
    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final BookingRepository bookingRepository;
    private final InventoryRepository inventoryRepository;
    @Override
    @Transactional
    public BookingDTO initialiseBooking(BookingRequestDTO bookingRequestDTO) {
        log.info("Initialising Booking for hotel : {}, room: {}, date: {}-{}",bookingRequestDTO.getHotelId(),bookingRequestDTO.getRoomId(),bookingRequestDTO.getCheckInDate(),bookingRequestDTO.getCheckOutDate());
        //Step1 : Get the hotel
        Hotel hotel = hotelRepository.findById(bookingRequestDTO.getHotelId()).orElseThrow(()->  new ResourceNotFoundException("Hotel not found with id: "+bookingRequestDTO.getHotelId()));
        Room room = roomRepository.findById(bookingRequestDTO.getRoomId()).orElseThrow(()-> new ResourceNotFoundException("Room not found with id: "+bookingRequestDTO.getRoomId()));
        List<Inventory> inventoryList = inventoryRepository.findAndLockAvailableInventory(
                bookingRequestDTO.getRoomId(),
                bookingRequestDTO.getCheckInDate(),
                bookingRequestDTO.getCheckOutDate(),
                bookingRequestDTO.getRoomsCount()
        );
        long daysCount = ChronoUnit.DAYS.between(bookingRequestDTO.getCheckInDate(),bookingRequestDTO.getCheckOutDate())+1;
        if(inventoryList.size() != daysCount){
            throw new IllegalStateException("Room is not available anymore");
        }
        //Reserve the room/update the booked count of inventories
        for(Inventory inventory: inventoryList){
            inventory.setReservedCount(inventory.getReservedCount()+bookingRequestDTO.getRoomsCount());
        }
        inventoryRepository.saveAll(inventoryList);

        //TODO: Dynamic pricing amount calculation

        //Create the booking
        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.RESERVED)
                .hotel(hotel)
                .room(room)
                .checkInDate(bookingRequestDTO.getCheckInDate())
                .checkOutDate(bookingRequestDTO.getCheckOutDate())
                .user(getCurrentUser())
                .roomsCount(bookingRequestDTO.getRoomsCount())
                .amount(BigDecimal.TEN)
                .build();
        booking = bookingRepository.save(booking);

        return modelMapper.map(booking, BookingDTO.class);

    }

    @Override
    public BookingDTO addGuest(List<GuestDTO> guestDTOList, Long bookingId) {
        log.info("Adding Guest  for Booking with id : {}",bookingId);
        //Step1 : Get the Booking
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()->  new ResourceNotFoundException("Booking not found with id: "+bookingId));
        User user = getCurrentUser();
        if(!user.equals(booking.getUser())){
            throw new UnauthorizedException("Booking does not belong to this user with id: "+user.getId());

        }

        //Step2 : Check if the booking has Expired
        if(hasBoookingExpired(booking)){
            throw new IllegalStateException("Booking has already expired");
        }
        if(booking.getBookingStatus()!=BookingStatus.RESERVED){
            throw new IllegalStateException("Booking is not under reserved state, cannot add guests");
        }
        for(GuestDTO guestDTO:guestDTOList){
            Guest guest = modelMapper.map(guestDTO, Guest.class);
            guest.setUser(getCurrentUser());
            guest = guestRepository.save(guest);
            booking.getGuests().add(guest);
        }
        booking.setBookingStatus(BookingStatus.GUESTS_ADDED);
        booking = bookingRepository.save(booking);
        return modelMapper.map(booking,BookingDTO.class);


    }

    @Override
    @Transactional
    public void deleteAllBookingsByRoom(Long roomId) {


        if (!roomRepository.existsById(roomId)) {
            throw new ResourceNotFoundException(
                    "Room with roomId not found: " + roomId
            );
        }


        boolean hasBookings = bookingRepository.existsByRoom_Id(roomId);

        if (!hasBookings) {
            return;
        }


        bookingRepository.deleteAllByRoomId(roomId);
    }

    public Boolean hasBoookingExpired(Booking booking){
        return booking.getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now());
    }
    public User getCurrentUser(){

        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }

}
