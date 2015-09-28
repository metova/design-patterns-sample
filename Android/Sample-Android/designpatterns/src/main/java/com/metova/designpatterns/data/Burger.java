package com.metova.designpatterns.data;

public class Burger implements Restaurant{

    long generated_id = 0;
    String id = null;
    String name = null;
    String phone = null;
    String url = null;
    String imageUrl = null;
    String ratingUrl = null;
    double rating = 0;
    int reviewCount = 0;
    String address = null;
    String city = null;
    String state = null;
    String zipCode = null;

    @Override
    public long getGenerated_id() {
        return generated_id;
    }

    @Override
    public void setGenerated_id(long generated_id) {
        this.generated_id = generated_id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String getRatingUrl() {
        return ratingUrl;
    }

    @Override
    public void setRatingUrl(String ratingUrl) {
        this.ratingUrl = ratingUrl;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public int getReviewCount() {
        return reviewCount;
    }

    @Override
    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String getZipCode() {
        return zipCode;
    }

    @Override
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
