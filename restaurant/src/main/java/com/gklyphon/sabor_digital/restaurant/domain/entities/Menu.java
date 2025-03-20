package com.gklyphon.sabor_digital.restaurant.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

/**
 * Entity representing a menu in a restaurant.
 * A menu consists of multiple {@link MenuItem} elements and belongs to a {@link Restaurant}.
 * It also extends {@link Auditable} to include automatic auditing fields.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
@Entity
@Table(name = "menus")
public class Menu extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MenuItem> menuItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Restaurant restaurant;

    /**
     * Default constructor.
     */
    public Menu() {
    }

    /**
     * Constructs a new {@code Menu} instance with the given parameters.
     *
     * @param id The unique identifier of the menu.
     * @param name The name of the menu.
     * @param menuItems The list of menu items in this menu.
     * @param restaurant The restaurant that owns this menu.
     */
    public Menu(Long id, String name, List<MenuItem> menuItems, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.menuItems = menuItems;
        this.restaurant = restaurant;
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

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Menu(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.menuItems = builder.menuItems;
        this.restaurant = builder.restaurant;
    }

    public static class Builder {
        private Long id;
        private String name;
        private List<MenuItem> menuItems;
        private Restaurant restaurant;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String id) {
            this.name = name;
            return this;
        }

        public Builder menuItems(List<MenuItem> menuItems) {
            this.menuItems = menuItems;
            return this;
        }

        public Builder restaurant(Restaurant restaurant) {
            this.restaurant = restaurant;
            return this;
        }

        public Menu build() {
            return new Menu(this);
        }
    }

}
