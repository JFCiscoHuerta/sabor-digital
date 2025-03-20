package com.gklyphon.sabor_digital.table.application.services;

import com.gklyphon.sabor_digital.table.application.dtos.TableDto;
import com.gklyphon.sabor_digital.table.domain.models.Table;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing tables.
 * Extends the generic {@link IService} interface for basic CRUD operations.
 * Provides additional methods for retrieving tables by restaurant ID and by a list of table IDs.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
public interface ITableService extends IService<Table, TableDto> {

    /**
     * Retrieves a paginated list of tables for a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @param pageable Pagination information.
     * @return A paginated list of tables.
     */
    Page<Table> findAllByRestaurantId(Long restaurantId, Pageable pageable);

    /**
     * Retrieves a list of tables by their IDs.
     *
     * @param ids The list of table IDs.
     * @return A list of tables.
     */
    List<Table> findByIdIn(List<Long> ids);
}
