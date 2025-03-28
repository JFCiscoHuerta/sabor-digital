package com.gklyphon.sabor_digital.waiter.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Data Transfer Object (DTO) for Waiter information.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
public class WaiterDto {

    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String phone;
    @Email
    private String email;
    private List<Long> tablesId;
    @NotNull
    private Long restaurantId;

    /**
     * Default constructor.
     */
    public WaiterDto() {
    }

    /**
     * Parameterized constructor for creating a WaiterDto.
     *
     * @param firstname   The first name of the waiter.
     * @param lastname    The last name of the waiter.
     * @param phone       The phone number of the waiter.
     * @param email       The email address of the waiter.
     * @param tablesId    List of table IDs assigned to the waiter.
     * @param restaurantId The ID of the restaurant where the waiter works.
     */
    public WaiterDto(String firstname, String lastname, String phone, String email, List<Long> tablesId, Long restaurantId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.tablesId = tablesId;
        this.restaurantId = restaurantId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getTablesId() {
        return tablesId;
    }

    public void setTablesId(List<Long> tablesId) {
        this.tablesId = tablesId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public WaiterDto(Builder builder) {
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.phone = builder.phone;
        this.email = builder.email;
        this.tablesId = builder.tablesId;
        this.restaurantId = builder.restaurantId;
    }

    public static class Builder {
        private String firstname;
        private String lastname;
        private String phone;
        private String email;
        private List<Long> tablesId;
        private Long restaurantId;

        public Builder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder tablesId(List<Long> tablesId) {
            this.tablesId = tablesId;
            return this;
        }

        public Builder restaurantId(Long restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public WaiterDto build() {
            return new WaiterDto(this);
        }

    }

}
