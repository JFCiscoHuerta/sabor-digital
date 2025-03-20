package com.gklyphon.sabor_digital.waiter.infrastructure.repositories;

import com.gklyphon.sabor_digital.waiter.domain.models.Waiter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing waiter entities.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
public interface IWaiterRepository extends JpaRepository<Waiter, Long> {

    /**
     * Finds a paginated list of waiters by restaurant ID.
     *
     * @param id The ID of the restaurant.
     * @param pageable Pagination details.
     * @return A paginated list of waiters belonging to the specified restaurant.
     */
    Page<Waiter> findAllByRestaurantId(Long id, Pageable pageable);

    /**
     * Finds a list of waiters by their IDs.
     *
     * @param ids The list of waiter IDs.
     * @return A list of waiters matching the provided IDs.
     */
    List<Waiter> findByIdIn(List<Long> ids);
}
