package com.gklyphon.sabor_digital.restaurant.utils;

import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuDto;
import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuItemDto;
import com.gklyphon.sabor_digital.restaurant.application.dtos.RestaurantDto;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Menu;
import com.gklyphon.sabor_digital.restaurant.domain.entities.MenuItem;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Restaurant;

import java.math.BigDecimal;
import java.util.List;

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

    public static final Menu MENU = new Menu.Builder()
            .id(1L)
            .name("My Menu")
            .menuItems(List.of(new MenuItem()))
            .restaurant(new Restaurant())
            .build();

    public static final MenuDto MENU_DTO = new MenuDto.Builder()
            .name("My Menu")
            .menuItems(List.of(new MenuItem()))
            .restaurantId(1L)
            .build();

    public static final MenuItem MENU_ITEM = new MenuItem.Builder()
            .id(1L)
            .name("Menu Item")
            .menu(new Menu())
            .preparationTime(123)
            .price(new BigDecimal("123"))
            .build();

    public static final MenuItemDto MENU_ITEM_DTO = new MenuItemDto.Builder()
            .name("Menu Item")
            .menuId(1L)
            .preparationTime(123)
            .price(new BigDecimal("123"))
            .build();
}
