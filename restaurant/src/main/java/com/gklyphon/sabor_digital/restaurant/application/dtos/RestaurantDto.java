package com.gklyphon.sabor_digital.restaurant.application.dtos;

public class RestaurantDto {

    private String name;
    private String address;
    private String phone;
    private String website;
    private String logo;

    public RestaurantDto() {
    }

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
