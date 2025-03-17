package com.gklyphon.sabor_digital.order.domain.models;

import com.gklyphon.sabor_digital.order.domain.models.enums.OrderStatus;
import com.gklyphon.sabor_digital.order.domain.models.enums.OrderType;
import com.gklyphon.sabor_digital.order.domain.models.enums.PaymentType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Entity representing an order in the system. Contains details about the order,
 * such as the items, associated restaurant, waiter, price, and status.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
@Entity
@Table(name = "orders")
public class Order extends Auditable {

    /**
     * Unique identifier for the order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * List of item IDs included in the order.
     */
    private List<Long> itemsId;

    /**
     * Identifier of the restaurant associated with the order.
     */
    private Long restaurantId;

    /**
     * Identifier of the waiter handling the order.
     */
    private Long waiterId;

    /**
     * Total price of the order.
     */
    private BigDecimal price;

    /**
     * Identifier of the table where the order was placed.
     */
    private Long tableId;

    /**
     * Identifier of the table where the order was placed.
     */
    @Enumerated(value = EnumType.STRING)
    private OrderType orderType;

    /**
     * The payment type used for the order (e.g., cash, credit).
     */
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    /**
     * The current status of the order.
     */
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    /**
     * Default constructor.
     */
    public Order() {
    }

    /**
     * Parameterized constructor for creating an order instance.
     * @param id
     * @param itemsId
     * @param restaurantId
     * @param waiterId
     * @param price
     * @param tableId
     * @param orderType
     * @param paymentType
     * @param orderStatus
     */
    public Order(Long id, List<Long> itemsId, Long restaurantId, Long waiterId, BigDecimal price, Long tableId, OrderType orderType, PaymentType paymentType, OrderStatus orderStatus) {
        this.id = id;
        this.itemsId = itemsId;
        this.restaurantId = restaurantId;
        this.waiterId = waiterId;
        this.price = price;
        this.tableId = tableId;
        this.orderType = orderType;
        this.paymentType = paymentType;
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    /**
     * Parameterized builder constructor
     * @param builder
     */
    public Order(Builder builder) {
        this.id = builder.id;
        this.itemsId = builder.itemsId;
        this.restaurantId = builder.restaurantId;
        this.waiterId = builder.waiterId;
        this.price = builder.price;
        this.tableId = builder.tableId;
        this.orderType = builder.orderType;
        this.paymentType = builder.paymentType;
        this.orderStatus = builder.orderStatus;
    }

    /**
     * Builder class for constructing Order instances.
     */
    public static class Builder {
        private Long id;
        private List<Long> itemsId;
        private Long restaurantId;
        private Long waiterId;
        private BigDecimal price;
        private Long tableId;
        private OrderType orderType;
        private PaymentType paymentType;
        private OrderStatus orderStatus;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

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

        /**
         * Builds the order instance
         * @return the constructed Order object
         */
        public Order build() {
            return new Order(this);
        }

    }

}
