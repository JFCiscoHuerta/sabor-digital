package com.gklyphon.sabor_digital.restaurant.application.dtos;

import com.gklyphon.sabor_digital.restaurant.domain.entities.MenuItem;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Restaurant;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Data Transfer Object (DTO) for Menu.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
public class MenuDto {

    @NotEmpty(message = "The name cannot be empty.")
    private String name;

    private List<MenuItem> menuItems;

    @NotNull(message = "The restaurantId cannot be null.")
    private Long restaurantId;

    /**
     * Default constructor.
     */
    public MenuDto() {
    }

    /**
     * Constructs a new {@code MenuDto} with the specified attributes.
     *
     * @param name         The name of the menu.
     * @param menuItems    The list of menu items associated with the menu.
     * @param restaurantId The ID of the restaurant that owns the menu.
     */
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
