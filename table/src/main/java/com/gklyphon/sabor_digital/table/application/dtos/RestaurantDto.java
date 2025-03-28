package com.gklyphon.sabor_digital.table.application.dtos;

/**
 * Data Transfer Object (DTO) for representing a restaurant.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
public class RestaurantDto {

    private String name;
    private String address;
    private String phone;
    private String website;
    private String logo;

    /**
     * Default constructor.
     */
    public RestaurantDto() {
    }

    /**
     * Parameterized constructor for creating a RestaurantDto object.
     *
     * @param name    The name of the restaurant.
     * @param address The address of the restaurant.
     * @param phone   The contact phone number of the restaurant.
     * @param website The website URL of the restaurant.
     * @param logo    The URL or path of the restaurant's logo.
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
