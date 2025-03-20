package com.gklyphon.sabor_digital.restaurant.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

/**
 * Entity representing a restaurant.
 * A restaurant can have multiple {@link Menu} instances associated with it.
 * It also extends {@link Auditable} to include automatic auditing fields.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
@Entity
@Table(name = "restaurants")
public class Restaurant extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String website;
    private String logo;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Menu> menus;

    /**
     * Default constructor.
     */
    public Restaurant() {
    }

    /**
     * Constructs a new {@code Restaurant} instance with the given parameters.
     *
     * @param id The unique identifier of the restaurant.
     * @param name The name of the restaurant.
     * @param address The address of the restaurant.
     * @param phone The contact phone number.
     * @param website The website URL.
     * @param logo The logo image URL or path.
     * @param menus The list of menus associated with this restaurant.
     */
    public Restaurant(Long id, String name, String address, String phone, String website, String logo, List<Menu> menus) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.logo = logo;
        this.menus = menus;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Restaurant(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.address = builder.address;
        this.phone = builder.phone;
        this.website = builder.website;
        this.logo = builder.logo;
        this.menus = builder.menus;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String address;
        private String phone;
        private String website;
        private String logo;
        private List<Menu> menus;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder website(String website) {
            this.website = website;
            return this;
        }

        public Builder logo(String logo) {
            this.logo = logo;
            return this;
        }

        public Builder menus(List<Menu> menus) {
            this.menus = menus;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
    }
}
