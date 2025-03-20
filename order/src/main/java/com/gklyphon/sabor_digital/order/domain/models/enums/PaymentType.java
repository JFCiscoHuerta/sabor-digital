package com.gklyphon.sabor_digital.order.domain.models.enums;

/**
 * Enum representing the possible types of a payment.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
public enum PaymentType {

    /**
     * Payment made by using cash.
     */
    CASH,

    /**
     * Payment made by using credit card.
     */
    CREDIT_CARD,

    /**
     * Payment made by using debit card.
     */
    DEBIT_CARD,

    /**
     * Payment made by using a digital wallet service.
     */
    DIGITAL_WALLET
}
