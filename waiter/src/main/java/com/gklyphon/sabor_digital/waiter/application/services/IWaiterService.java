package com.gklyphon.sabor_digital.waiter.application.services;

import com.gklyphon.sabor_digital.waiter.application.dto.WaiterDto;
import com.gklyphon.sabor_digital.waiter.domain.models.Waiter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing waiters.
 * Extends the generic {@link IService} interface for basic CRUD operations.
 * Provides additional methods for retrieving tables by restaurant ID and by a list of table IDs.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
public interface IWaiterService extends IService<Waiter, WaiterDto> {

    /**
     * Retrieves a paginated list of waiters belonging to a specific restaurant.
     *
     * @param restaurantId the ID of the restaurant.
     * @param pageable the pagination and sorting information.
     * @return a paginated list of waiters.
     */
    Page<Waiter> findAllByRestaurantId(Long restaurantId, Pageable pageable);

    /**
     * Retrieves a list of waiters by their IDs.
     *
     * @param ids the list of waiter IDs.
     * @return a list of found waiters.
     */
    List<Waiter> findByIdIn(List<Long> ids);
}
