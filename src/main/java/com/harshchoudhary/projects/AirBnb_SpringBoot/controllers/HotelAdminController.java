package com.harshchoudhary.projects.AirBnb_SpringBoot.controllers;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.HotelDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.service.IHotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class HotelAdminController {
    private final IHotelService iHotelService;

    //API:1 Creating New Hotel
    @PostMapping
    public ResponseEntity<HotelDTO>createNewHotel(@RequestBody HotelDTO hotelDTO){
        log.info("Attempting to create a new hotel with name: "+ hotelDTO.getName());
        HotelDTO hotel = iHotelService.createNewHotel(hotelDTO);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }
    //API:2 Get the Hotel by id
    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDTO>getHotelById(@PathVariable Long hotelId){
        HotelDTO hotelDTO = iHotelService.getHotelById(hotelId);
        return ResponseEntity.ok(hotelDTO);
    }
    //API:3 Update hotel By id
    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDTO>updateHotelById(@RequestBody HotelDTO hotelDTO,@PathVariable Long hotelId){
        HotelDTO hotel = iHotelService.updateHotelById(hotelId,hotelDTO);
        return ResponseEntity.ok(hotel);
    }

    //API:4 Delete Hotel by id
    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void>deleteHotelById(@PathVariable Long hotelId){
        iHotelService.deleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }
    //API:5 Make hotel Active
    @PatchMapping("/{hotelId}")
    public ResponseEntity<Void>activateHotel(@PathVariable Long hotelId){
        iHotelService.activateHotel(hotelId);
        return ResponseEntity.noContent().build();

    }

}
