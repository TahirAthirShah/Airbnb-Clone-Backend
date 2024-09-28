package com.airbnb.service;

import com.airbnb.entity.Favourite;
import com.airbnb.entity.Property;
import com.airbnb.entity.PropertyUser;
import com.airbnb.repository.FavouriteRepository;
import com.airbnb.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavouriteService {
    private FavouriteRepository favouriteRepository;
    private PropertyRepository propertyRepository;

    public FavouriteService(FavouriteRepository favouriteRepository, PropertyRepository propertyRepository) {
        this.favouriteRepository = favouriteRepository;
        this.propertyRepository = propertyRepository;
    }
    public Favourite addFavourite(Long propertyId, PropertyUser user){
        Optional<Property> opProperty = propertyRepository.findById(propertyId);
        Property property = opProperty.get();
        Favourite favourite = new Favourite();
        favourite.setPropertyUser(user);
        favourite.setProperty(property);
        favourite.setIsFavourite(true);
        return favouriteRepository.save(favourite);
    }

    public List<Favourite> fetchAllFavourite(PropertyUser user){
        List<Favourite> favourites = favouriteRepository.fetchAllFavourite(user);
        return favourites;
    }
}
