package com.gklyphon.sabor_digital.order.application.dtos;

import com.gklyphon.sabor_digital.order.domain.models.enums.OrderStatus;
import com.gklyphon.sabor_digital.order.domain.models.enums.OrderType;
import com.gklyphon.sabor_digital.order.domain.models.enums.PaymentType;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

    private List<Long> itemsId;
    private Long restaurantId;
    private Long waiterId;
    private BigDecimal price;
    private Long tableId;
    private OrderType orderType;
    private PaymentType paymentType;
    private OrderStatus orderStatus;

    public OrderDto() {
    }

    public OrderDto(List<Long> itemsId, Long restaurantId, Long waiterId, BigDecimal price, Long tableId, OrderType orderType, PaymentType paymentType, OrderStatus orderStatus) {
        this.itemsId = itemsId;
        this.restaurantId = restaurantId;
        this.waiterId = waiterId;
        this.price = price;
        this.tableId = tableId;
        this.orderType = orderType;
        this.paymentType = paymentType;
        this.orderStatus = orderStatus;
    }

    public List<Long> getItemsId() {
        return itemsId;
    }

    public void setItemsId(List<Long> itemsId) {
        this.itemsId = itemsId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Long waiterId) {
        this.waiterId = waiterId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    public OrderDto(Builder builder) {
        this.itemsId = builder.itemsId;
        this.restaurantId = builder.restaurantId;
        this.waiterId = builder.waiterId;
        this.price = builder.price;
        this.tableId = builder.tableId;
        this.orderType = builder.orderType;
        this.paymentType = builder.paymentType;
        this.orderStatus = builder.orderStatus;
    }

    public static class Builder {
        private List<Long> itemsId;
        private Long restaurantId;
        private Long waiterId;
        private BigDecimal price;
        private Long tableId;
        private OrderType orderType;
        private PaymentType paymentType;
        private OrderStatus orderStatus;

        public Builder itemsId(List<Long> itemsId) {
            this.itemsId = itemsId;
            return this;
        }

        public Builder restaurantId(Long restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public Builder waiterId(Long waiterId) {
            this.waiterId = waiterId;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder tableId(Long tableId) {
            this.tableId = tableId;
            return this;
        }

        public Builder orderType(OrderType orderType) {
            this.orderType = orderType;
            return this;
        }

        public Builder paymentType(PaymentType paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public Builder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public OrderDto build() {
            return new OrderDto(this);
        }

    }


}
