package com.gklyphon.sabor_digital.waiter.domain.models;

import jakarta.persistence.*;

import java.util.List;

/**
 * Entity representing a Waiter.
 * Each waiter is associated with a restaurant and can be assigned to multiple tables.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
@Entity
@Table(name = "waiters")
public class Waiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    @ElementCollection
    @CollectionTable(name = "waiter_tables", joinColumns = @JoinColumn(name = "waiter_id"))
    @Column(name = "table_id")
    private List<Long> tablesId;
    private Long restaurantId;

    /**
     * Default constructor
     */
    public Waiter() {
    }

    /**
     * Constructs a new Waiter with the given parameters.
     *
     * @param id          The ID of the waiter.
     * @param firstname   The first name of the waiter.
     * @param lastname    The last name of the waiter.
     * @param phone       The phone number of the waiter.
     * @param email       The email address of the waiter.
     * @param tablesId    The list of table IDs assigned to the waiter.
     * @param restaurantId The restaurant ID associated with the waiter.
     */
    public Waiter(Long id, String firstname, String lastname, String phone, String email, List<Long> tablesId, Long restaurantId) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.tablesId = tablesId;
        this.restaurantId = restaurantId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Waiter(Builder builder) {
        this.id = builder.id;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.phone = builder.phone;
        this.email = builder.email;
        this.tablesId = builder.tablesId;
        this.restaurantId = builder.restaurantId;
    }

    public static class Builder {
        private Long id;
        private String firstname;
        private String lastname;
        private String phone;
        private String email;
        private List<Long> tablesId;
        private Long restaurantId;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

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

        public Waiter build() {
            return new Waiter(this);
        }

    }

}
