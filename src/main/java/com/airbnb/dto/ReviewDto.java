package com.airbnb.dto;

import com.airbnb.entity.Property;
import com.airbnb.entity.PropertyUser;

public class ReviewDto {

    private PropertyUser user;
    private Property property;
    private String content;
    private Integer rating;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PropertyUser getUser() {
        return user;
    }

    public void setUser(PropertyUser user) {
        this.user = user;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
