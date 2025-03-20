package com.gklyphon.sabor_digital.order;

import com.gklyphon.sabor_digital.order.application.dtos.OrderDto;
import com.gklyphon.sabor_digital.order.domain.models.Order;
import com.gklyphon.sabor_digital.order.domain.models.enums.OrderStatus;
import com.gklyphon.sabor_digital.order.domain.models.enums.OrderType;
import com.gklyphon.sabor_digital.order.domain.models.enums.PaymentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.List;

public class TestData {

    public static final Order ORDER = new Order.Builder()
            .id(1L)
            .orderStatus(OrderStatus.PENDING)
            .orderType(OrderType.DINE_IN)
            .itemsId(List.of(1L, 2L))
            .paymentType(PaymentType.CASH)
            .price(new BigDecimal("123"))
            .restaurantId(1L)
            .tableId(1L)
            .waiterId(1L)
            .build();

    public static final OrderDto ORDER_DTO = new OrderDto.Builder()
            .orderStatus(OrderStatus.PENDING)
            .orderType(OrderType.DINE_IN)
            .itemsId(List.of(1L, 2L))
            .paymentType(PaymentType.CASH)
            .price(new BigDecimal("123"))
            .restaurantId(1L)
            .tableId(1L)
            .waiterId(1L)
            .build();

    public static final Page<Order> ORDER_PAGE = new PageImpl<>(List.of(ORDER));

}
