package com.lcwd.hotel.service.impl;

import com.lcwd.hotel.entities.HotelEntity;
import com.lcwd.hotel.exception.ResourceNotFoundException;
import com.lcwd.hotel.repository.HotelRepository;
import com.lcwd.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository repository;

    @Override
    public HotelEntity createHotel(HotelEntity hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return repository.save(hotel);
    }

    @Override
    public List<HotelEntity> getAllHotel() {
        return repository.findAll();
    }

    @Override
    public HotelEntity getHotelById(String hotelId) throws ResourceNotFoundException {
        return repository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id is not found on server !! " + hotelId));
    };
}
