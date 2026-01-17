package com.harshchoudhary.projects.AirBnb_SpringBoot.controllers;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.HotelDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.HotelInfoDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.HotelSearchRequest;
import com.harshchoudhary.projects.AirBnb_SpringBoot.service.IHotelService;
import com.harshchoudhary.projects.AirBnb_SpringBoot.service.IInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {

    private final IInventoryService iInventoryService;
    private final IHotelService iHotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelDTO>>searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest){
       Page<HotelDTO>page =  iInventoryService.searchHotels(hotelSearchRequest);
       return ResponseEntity.ok(page);

    }
    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDTO>getHotelInfo(@PathVariable Long hotelId){
        return ResponseEntity.ok(iHotelService.getInfobyHotelId(hotelId));
    }
}
