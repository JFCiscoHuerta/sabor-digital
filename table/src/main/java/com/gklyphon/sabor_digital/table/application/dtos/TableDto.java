package com.gklyphon.sabor_digital.table.application.dtos;

import com.gklyphon.sabor_digital.table.domain.models.Table;

import java.util.List;

public class TableDto {

    private Long restaurantId;
    private String tableIdentifier;
    private List<Long> waitersId;

    public TableDto() {
    }

    public TableDto(Long restaurantId, String tableIdentifier, List<Long> waitersId) {
        this.restaurantId = restaurantId;
        this.tableIdentifier = tableIdentifier;
        this.waitersId = waitersId;
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

    public TableDto(Builder builder) {
        this.restaurantId = builder.restaurantId;
        this.tableIdentifier = builder.tableIdentifier;
        this.waitersId = builder.waitersId;
    }

    public static class Builder {
        private Long restaurantId;
        private String tableIdentifier;
        private List<Long> waitersId;

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

        public TableDto build() {
            return new TableDto(this);
        }

    }


}
