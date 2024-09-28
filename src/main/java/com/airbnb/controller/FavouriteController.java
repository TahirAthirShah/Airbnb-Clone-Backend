package com.airbnb.controller;

import com.airbnb.entity.Favourite;
import com.airbnb.entity.PropertyUser;
import com.airbnb.service.FavouriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/favourite")
public class FavouriteController {

    private FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @PostMapping("/addFavourite")
    public ResponseEntity<Favourite> addFavourite(@RequestParam Long propertyId,
                                                  @AuthenticationPrincipal PropertyUser user){
        Favourite saved = favouriteService.addFavourite(propertyId, user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/allFavourite")
    public ResponseEntity<List<Favourite>> fetchAllFavourite(@AuthenticationPrincipal PropertyUser user){
        List<Favourite>  allFavourite = favouriteService.fetchAllFavourite(user);
        return new ResponseEntity<>(allFavourite, HttpStatus.OK);
    }
}
