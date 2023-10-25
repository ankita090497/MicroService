package com.lcwd.hotel.service;

import com.lcwd.hotel.entities.HotelEntity;
import com.lcwd.hotel.exception.ResourceNotFoundException;

import java.util.List;

public interface HotelService {

    HotelEntity createHotel(HotelEntity hotel);

    List<HotelEntity> getAllHotel();

    HotelEntity getHotelById(String hotelId) throws ResourceNotFoundException;
}
