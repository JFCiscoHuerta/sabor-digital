package com.gklyphon.sabor_digital.order.domain.models.enums;

/**
 * Enum representing the possible statuses of an order.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
public enum OrderStatus {

    /**
     * Order has been created but not yet confirmed.
     */
    PENDING,

    /**
     * Order has been confirmed.
     */
    CONFIRMED,

    /**
     * Order is ready to be served or picked up.
     */
    READY,

    /**
     * Order has been delivered.
     */
    DELIVERED,

    /**
     * Order has been canceled.
     */
    CANCELLED
}
