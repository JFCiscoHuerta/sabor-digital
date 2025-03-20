package com.gklyphon.sabor_digital.order.application.services;

import com.gklyphon.sabor_digital.order.application.dtos.OrderDto;
import com.gklyphon.sabor_digital.order.domain.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for handling operations related to orders.
 * Extends the genetic {@link IService} interface.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
public interface IOrderService extends IService<Order, OrderDto> {

    /**
     * Finds all orders associated with a specific restaurant, supporting pagination.
     *
     * @param restaurantId the ID of the restaurant
     * @param pageable the pagination information
     * @return a page of orders related to the specified restaurant
     */
    Page<Order> findAllByRestaurantId(Long restaurantId, Pageable pageable);
}
