package com.gklyphon.sabor_digital.order.application.services;

import com.gklyphon.sabor_digital.order.application.dtos.OrderDto;
import com.gklyphon.sabor_digital.order.domain.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService extends IService<Order, OrderDto> {

    Page<Order> findAllByRestaurantId(Long restaurantId, Pageable pageable);

}
