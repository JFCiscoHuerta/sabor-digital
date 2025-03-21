package com.gklyphon.sabor_digital.restaurant.application.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for Restaurant.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
public class RestaurantDto {

    @NotEmpty(message = "The name cannot be empty.")
    @Size(max = 100, message = "The name must not exceed 100 characters.")
    private String name;

    @NotEmpty(message = "The address cannot be empty.")
    @Size(max = 255, message = "The address must not exceed 255 characters.")
    private String address;

    @NotEmpty(message = "The phone number cannot be empty.")
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Invalid phone number format.")
    private String phone;
    private String website;
    private String logo;

    /**
     * Default constructor.
     */
    public RestaurantDto() {
    }

    /**
     * Constructs a new {@code RestaurantDto} with the specified attributes.
     *
     * @param name     The name of the restaurant.
     * @param address  The address of the restaurant.
     * @param phone    The contact phone number of the restaurant.
     * @param website  The website URL of the restaurant.
     * @param logo     The logo URL or image reference of the restaurant.
     */
    public RestaurantDto(String name, String address, String phone, String website, String logo) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.logo = logo;
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


    public RestaurantDto(Builder builder) {
        this.name = builder.name;
        this.address = builder.address;
        this.phone = builder.phone;
        this.website = builder.website;
        this.logo = builder.logo;
    }

    public static class Builder {
        private String name;
        private String address;
        private String phone;
        private String website;
        private String logo;

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

        public RestaurantDto build() {
            return new RestaurantDto(this);
        }
    }
}
