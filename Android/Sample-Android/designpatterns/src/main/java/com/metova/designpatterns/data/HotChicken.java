package com.metova.designpatterns.data;

public class HotChicken implements Restaurant {

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

    public long getGenerated_id() {
        return generated_id;
    }

    public void setGenerated_id(long generated_id) {
        this.generated_id = generated_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRatingUrl() {
        return ratingUrl;
    }

    public void setRatingUrl(String ratingUrl) {
        this.ratingUrl = ratingUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
