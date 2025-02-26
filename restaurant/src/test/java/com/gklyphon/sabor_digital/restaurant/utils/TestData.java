package com.gklyphon.sabor_digital.restaurant.utils;

import com.gklyphon.sabor_digital.restaurant.application.dtos.RestaurantDto;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Restaurant;

public class TestData {

    public static final Restaurant RESTAURANT = new Restaurant.Builder()
            .id(1L)
            .name("My Restaurant")
            .address("My address")
            .logo("My logo")
            .phone("My phone")
            .website("My website")
            .build();

    public static final RestaurantDto RESTAURANT_DTO = new RestaurantDto.Builder()
            .name("My Restaurant")
            .address("My address")
            .logo("My logo")
            .phone("My phone")
            .website("My website")
            .build();

}
