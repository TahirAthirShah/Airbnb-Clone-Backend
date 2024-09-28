package com.airbnb.service;

import com.airbnb.dto.CountryDto;
import com.airbnb.entity.Country;
import com.airbnb.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public CountryDto addCountry(CountryDto countryDto){
        Country country = new Country();
        country.setCountryName(countryDto.getCountryName());
        Country saved = countryRepository.save(country);
        countryDto.setCountryName(saved.getCountryName());
        return countryDto;
    }
}
