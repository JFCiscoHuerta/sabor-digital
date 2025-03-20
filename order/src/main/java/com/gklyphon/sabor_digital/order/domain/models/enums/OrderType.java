package com.gklyphon.sabor_digital.order.domain.models.enums;

/**
 * Enum representing the possible types of an order.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
public enum OrderType {

    /**
     * Order is to be consumed on the premises.
     */
    DINE_IN,

    /**
     * Order is to be taken away by the customer.
     */
    TAKEAWAY,

    /**
     * Order is to be delivered to the customer.
     */
    DELIVERY
}
