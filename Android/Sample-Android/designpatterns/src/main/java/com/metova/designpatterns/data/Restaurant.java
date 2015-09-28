package com.metova.designpatterns.data;

public interface Restaurant {

    long getGenerated_id();

    void setGenerated_id(long generated_id);

    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    String getPhone();

    void setPhone(String phone);

    String getUrl();

    void setUrl(String url);

    String getImageUrl();

    void setImageUrl(String imageUrl);

    String getRatingUrl();

    void setRatingUrl(String ratingUrl);

    double getRating();

    void setRating(double rating);

    int getReviewCount();

    void setReviewCount(int reviewCount);

    String getAddress();

    void setAddress(String address);

    String getCity();

    void setCity(String city);

    String getState();

    void setState(String state);

    String getZipCode();

    void setZipCode(String zipCode);

}
