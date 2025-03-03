package com.gklyphon.sabor_digital.table.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
@jakarta.persistence.Table(name = "tables")
public class Table extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long restaurantId;
    private String tableIdentifier;
    private List<Long> waitersId;

    public Table() {
    }

    public Table(Long id,Long restaurantId, String tableIdentifier, List<Long> waitersId) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.tableIdentifier = tableIdentifier;
        this.waitersId = waitersId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getTableIdentifier() {
        return tableIdentifier;
    }

    public void setTableIdentifier(String tableIdentifier) {
        this.tableIdentifier = tableIdentifier;
    }

    public List<Long> getWaitersId() {
        return waitersId;
    }

    public void setWaitersId(List<Long> waitersId) {
        this.waitersId = waitersId;
    }

    public Table(Builder builder) {
        this.id = builder.id;
        this.restaurantId = builder.restaurantId;
        this.tableIdentifier = builder.tableIdentifier;
        this.waitersId = builder.waitersId;
    }

    public static class Builder {
        private Long id;
        private Long restaurantId;
        private String tableIdentifier;
        private List<Long> waitersId;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder restaurantId(Long restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public Builder tableIdentifier(String tableIdentifier) {
            this.tableIdentifier = tableIdentifier;
            return this;
        }

        public Builder waitersId(List<Long> waitersId) {
            this.waitersId = waitersId;
            return this;
        }

        public Table build() {
            return new Table(this);
        }

    }

}
