package com.gklyphon.sabor_digital.restaurant.application.services;

import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuDto;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for managing menu-related operations.
 * Extends {@link IService} with {@link Menu} as the entity type
 * and {@link MenuDto} as the DTO type.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
public interface IMenuService extends IService<Menu, MenuDto> {

    /**
     * Retrieves a paginated list of menus.
     *
     * @param pageable The pagination and sorting information.
     * @return A {@link Page} containing a list of {@link Menu} objects.
     */
    Page<Menu> findAll(Pageable pageable);
}
