package com.gklyphon.sabor_digital.restaurant.application.services;

import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuItemDto;
import com.gklyphon.sabor_digital.restaurant.domain.entities.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing menu item-related operations.
 * Extends {@link IService} with {@link MenuItem} as the entity type
 * and {@link MenuItemDto} as the DTO type.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
public interface IMenuItemService extends IService<MenuItem, MenuItemDto> {

    /**
     * Retrieves a paginated list of menu items.
     *
     * @param pageable The pagination and sorting information.
     * @return A {@link Page} containing a list of {@link MenuItem} objects.
     */
    Page<MenuItem> findAll(Pageable pageable);

    /**
     * Retrieves a list of menu items by their unique identifiers.
     *
     * @param ids List of menu item IDs to retrieve.
     * @return List of {@link MenuItem} corresponding to the given IDs.
     */
    List<MenuItem> findByIdIn(List<Long> ids);
}
