package com.group12.trek.models;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, String> {
    Optional<Place> findByGeohash(String geohash);
}