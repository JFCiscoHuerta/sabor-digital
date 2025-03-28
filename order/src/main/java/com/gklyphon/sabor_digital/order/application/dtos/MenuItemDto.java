package com.gklyphon.sabor_digital.order.application.dtos;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) for MenuItem.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
public class MenuItemDto {

    private String name;
    private BigDecimal price;
    private int preparationTime;
    private Long menuId;

    /**
     * Default constructor.
     */
    public MenuItemDto() {
    }

    /**
     * Constructs a new {@code MenuItemDto} with the specified attributes.
     *
     * @param name            The name of the menu item.
     * @param price           The price of the menu item.
     * @param preparationTime The preparation time required for the menu item (in minutes).
     * @param menuId          The ID of the menu to which this item belongs.
     */
    public MenuItemDto(String name, BigDecimal price, int preparationTime, Long menuId) {
        this.name = name;
        this.price = price;
        this.preparationTime = preparationTime;
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public MenuItemDto(Builder builder) {
        this.preparationTime = builder.preparationTime;
        this.price = builder.price;
        this.name = builder.name;
        this.menuId = builder.menuId;
    }

    public static class Builder {
        private String name;
        private BigDecimal price;
        private int preparationTime;
        private Long menuId;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder preparationTime(int preparationTime) {
            this.preparationTime = preparationTime;
            return this;
        }

        public Builder menuId(Long menuId) {
            this.menuId = menuId;
            return this;
        }

        public MenuItemDto build() {
            return new MenuItemDto(this);
        }

    }


}
