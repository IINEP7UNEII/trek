package com.group12.trek.models;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
    
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }
    
    public List<Place> findAll() {
        return placeRepository.findAll();
    }
    
    public Place save(Place place) {
        return placeRepository.save(place);
    }

    public Optional<Place> findByGeohash(String geohash) {
        return placeRepository.findByGeohash(geohash);
    }
}