package com.metova.designpatterns.data;

public class RestaurantFactory {

    public enum RestaurantType {
        HOT_CHICKEN,
        BURGER,
    }

    public static Restaurant getRestaurant(RestaurantType type) {
        if (type.equals(RestaurantType.HOT_CHICKEN)) {
            return new HotChicken();
        } else if (type.equals(RestaurantType.BURGER)){
            return new Burger();
        }

        return null;
    }

}
