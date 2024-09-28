package com.airbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private JWTRequestFilter jwtRequestFilter;

    public SecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().cors().disable();
         //remember hap
        http.authorizeHttpRequests()
                .anyRequest().permitAll();
//                .requestMatchers("/api/v1/users/addUser","/api/v1/users/login")
//                .permitAll()
//                .requestMatchers("/api/v1/countries/addCountry","/api/v1/users/profile",
//                        "/api/v1/image/upload/file/myawsbucket7079/property/{propertyId}","/api/v1/property/addProperty",
//                        "/api/v1/location/addLocation").hasRole("ADMIN")
//                .requestMatchers("/api/api/v1/users/profile","/api/v1/booking/createBooking/{propertyId}",
//                        "/api/v1/property/Spain", "/api/v1/favourite/addFavourite?propertyId=",
//                        "/api/v1/reviews/userReviews", "/api/v1/reviews/addReview/",
//                        "/api/v1/favourite/allFavourite").hasAnyRole("ADMIN","USER")
//                .anyRequest()
//                .authenticated();
        return http.build();
    }
}
