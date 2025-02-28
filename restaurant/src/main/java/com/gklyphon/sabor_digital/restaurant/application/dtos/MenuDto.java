package com.gklyphon.sabor_digital.restaurant.application.dtos;

import com.gklyphon.sabor_digital.restaurant.domain.entities.MenuItem;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Restaurant;

import java.util.List;

public class MenuDto {

    private String name;
    private List<MenuItem> menuItems;
    private Long restaurantId;

    public MenuDto() {
    }

    public MenuDto(String name, List<MenuItem> menuItems, Long restaurantId) {
        this.name = name;
        this.menuItems = menuItems;
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public MenuDto(Builder builder) {
        this.name = builder.name;
        this.menuItems = builder.menuItems;
        this.restaurantId = builder.restaurantId;
    }

    public static class Builder {
        private String name;
        private List<MenuItem> menuItems;
        private Long restaurantId;

        public Builder name(String id) {
            this.name = name;
            return this;
        }

        public Builder menuItems(List<MenuItem> menuItems) {
            this.menuItems = menuItems;
            return this;
        }

        public Builder restaurantId(Long restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public MenuDto build() {
            return new MenuDto(this);
        }
    }

}
