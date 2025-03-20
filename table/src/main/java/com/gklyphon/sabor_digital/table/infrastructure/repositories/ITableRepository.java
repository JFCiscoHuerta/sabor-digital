package com.gklyphon.sabor_digital.table.infrastructure.repositories;

import com.gklyphon.sabor_digital.table.domain.models.Table;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing {@link Table} entities.
 * Extends {@link JpaRepository} to provide basic CRUD operations.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
public interface ITableRepository extends JpaRepository<Table, Long> {

    /**
     * Retrieves a paginated list of tables for a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @param pageable The pagination information.
     * @return A paginated list of tables belonging to the given restaurant.
     */
    Page<Table> findAllByRestaurantId(Long restaurantId, Pageable pageable);

    /**
     * Retrieves a list of tables by their IDs.
     *
     * @param ids The list of table IDs to search for.
     * @return A list of tables matching the provided IDs.
     */
    List<Table> findByIdIn(List<Long> ids);
}
