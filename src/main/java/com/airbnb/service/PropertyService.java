package com.airbnb.service;

import com.airbnb.dto.PropertyDto;
import com.airbnb.entity.Country;
import com.airbnb.entity.Location;
import com.airbnb.entity.Property;
import com.airbnb.repository.CountryRepository;
import com.airbnb.repository.LocationRepository;
import com.airbnb.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    private PropertyRepository propertyRepository;

    private LocationRepository locationRepository;
    private CountryRepository countryRepository;

    public PropertyService(PropertyRepository propertyRepository, LocationRepository locationRepository, CountryRepository countryRepository) {
        this.propertyRepository = propertyRepository;
        this.locationRepository = locationRepository;
        this.countryRepository = countryRepository;
    }

    public Property addProperty(PropertyDto propertyDto){
        Location location = locationRepository.findById(propertyDto.getLocation())
                .orElseThrow(()->new IllegalArgumentException("Location not found"));
        Country country = countryRepository.findById(propertyDto.getCountry())
                .orElseThrow(() -> new IllegalArgumentException("Country not found"));
        Property property = mapToEntity(propertyDto, location, country);
        Property savedProperty = propertyRepository.save(property);
        return savedProperty;
//        PropertyDto dto = mapToDto(savedProperty, location, country);
//        return dto;
    }

    public List<Property> getProperties(String locationName){
        List<Property> properties = propertyRepository.fetchByLocationName(locationName);
        return properties;
    }

    Property mapToEntity(PropertyDto propertyDto, Location location, Country country){
        Property property = new Property();
        property.setLocation(location);
        property.setCountry(country);
        property.setPropertyName(propertyDto.getPropertyName());
        property.setBeds(propertyDto.getBeds());
        property.setBathrooms(propertyDto.getBathrooms());
        property.setBedrooms(propertyDto.getBedrooms());
        property.setGuests(propertyDto.getGuests());
        property.setNightlyPrice(propertyDto.getNightlyPrice());
        return property;
    }
    PropertyDto mapToDto(Property property, Location location, Country country){
        PropertyDto dto = new PropertyDto();
        dto.setCountry(country.getId());
        dto.setPropertyName(property.getPropertyName());
        dto.setLocation(location.getId());
        dto.setBathrooms(property.getBathrooms());
        dto.setBeds(property.getBeds());
        dto.setGuests(property.getGuests());
        dto.setBedrooms(property.getBedrooms());
        dto.setNightlyPrice(property.getNightlyPrice());
        return dto;
    }
}
