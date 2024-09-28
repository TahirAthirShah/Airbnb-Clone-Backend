package com.airbnb.service;

import com.airbnb.dto.LocationDto;
import com.airbnb.entity.Location;
import com.airbnb.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    public LocationDto addLocation(LocationDto locationDto){
        Location location = new Location();
        location.setLocationName(locationDto.getLocationName());
        Location saved = locationRepository.save(location);
        LocationDto dto = new LocationDto();
        dto.setLocationName(saved.getLocationName());
        return dto;
    }
}
