package com.airbnb.repository;

import com.airbnb.entity.Favourite;
import com.airbnb.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    @Query("select f from Favourite f where f.propertyUser=:user")
    List<Favourite> fetchAllFavourite(@Param("user")PropertyUser user);
}