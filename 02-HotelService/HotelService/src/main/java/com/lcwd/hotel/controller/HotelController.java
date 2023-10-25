package com.lcwd.hotel.controller;

import com.lcwd.hotel.entities.HotelEntity;
import com.lcwd.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelEntity> createHotel(@RequestBody HotelEntity hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelEntity> getHotelById(@PathVariable String hotelId){
        HotelEntity hotel = hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(hotel);

    }


    @GetMapping
    public ResponseEntity<List<HotelEntity>> getAllHotels(){
        List<HotelEntity> hotelList = hotelService.getAllHotel();
        return ResponseEntity.ok(hotelList);
    };
}
