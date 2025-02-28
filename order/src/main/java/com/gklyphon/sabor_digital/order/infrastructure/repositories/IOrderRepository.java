package com.gklyphon.sabor_digital.order.infrastructure.repositories;

import com.gklyphon.sabor_digital.order.domain.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByRestaurantId(Long restaurantId, Pageable pageable);

}
