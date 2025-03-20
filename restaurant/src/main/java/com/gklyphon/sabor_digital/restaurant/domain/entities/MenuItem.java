package com.gklyphon.sabor_digital.restaurant.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Entity representing an item in a menu.
 * A {@code MenuItem} belongs to a {@link Menu} and contains details such as name, price, and preparation time.
 * It also extends {@link Auditable} to include automatic auditing fields.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
@Entity
@Table(name = "menu_items")
public class MenuItem extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private int preparationTime;
    @ManyToOne
    private Menu menu;

    /**
     * Default constructor.
     */
    public MenuItem() {
    }

    /**
     * Constructs a new {@code MenuItem} instance with the given parameters.
     *
     * @param id The unique identifier of the menu item.
     * @param name The name of the menu item.
     * @param price The price of the menu item.
     * @param preparationTime The preparation time in minutes.
     * @param menu The menu to which this item belongs.
     */
    public MenuItem(Long id, String name, BigDecimal price, int preparationTime, Menu menu) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.preparationTime = preparationTime;
        this.menu = menu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public MenuItem(Builder builder) {
        this.id = builder.id;
        this.preparationTime = builder.preparationTime;
        this.price = builder.price;
        this.name = builder.name;
        this.menu = builder.menu;
    }

    public static class Builder {
        private Long id;
        private String name;
        private BigDecimal price;
        private int preparationTime;
        private Menu menu;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

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

        public Builder menu(Menu menu) {
            this.menu = menu;
            return this;
        }

        public MenuItem build() {
            return new MenuItem(this);
        }

    }

}
