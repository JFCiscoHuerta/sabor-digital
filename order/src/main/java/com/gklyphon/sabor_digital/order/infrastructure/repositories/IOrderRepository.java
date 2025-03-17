package com.gklyphon.sabor_digital.order.infrastructure.repositories;

import com.gklyphon.sabor_digital.order.domain.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing Order data from the database.
 *
 * <p>Extends JpaRepository to provide standard CRUD operating and custom queries</p>
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
public interface IOrderRepository extends JpaRepository<Order, Long> {

    /**
     * Finds all orders associated with a specific restaurant ID.
     *
     * @param restaurantId the ID of the restaurant
     * @param pageable pagination information
     * @return a page of orders associated with the specified restaurant ID
     */
    Page<Order> findAllByRestaurantId(Long restaurantId, Pageable pageable);
}
