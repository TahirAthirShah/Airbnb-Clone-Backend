package com.airbnb.controller;

import com.airbnb.dto.PropertyDto;
import com.airbnb.entity.Property;
import com.airbnb.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/property")
public class PropertyController {

    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/addProperty")
    public ResponseEntity<Property> addProperty(@RequestBody PropertyDto propertyDto){
        Property property = propertyService.addProperty(propertyDto);
        return new ResponseEntity<>(property, HttpStatus.CREATED);
    }

    @GetMapping("{locationName}")
    public ResponseEntity<List<Property>> fetchProperty(@PathVariable String locationName){
        List<Property> properties = propertyService.getProperties(locationName);
        return new ResponseEntity<>(properties,HttpStatus.OK);
    }
}
