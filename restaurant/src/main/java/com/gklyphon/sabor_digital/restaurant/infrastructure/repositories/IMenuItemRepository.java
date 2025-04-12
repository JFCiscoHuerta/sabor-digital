package com.gklyphon.sabor_digital.restaurant.infrastructure.repositories;

import com.gklyphon.sabor_digital.restaurant.domain.entities.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for {@link MenuItem} entities.
 * This interface extends {@link JpaRepository} to provide basic CRUD operations.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
public interface IMenuItemRepository extends JpaRepository<MenuItem, Long> {

    /**
     * Retrieves a list of menu items by their unique identifiers.
     *
     * @param ids List of menu item IDs to retrieve.
     * @return List of {@link MenuItem} corresponding to the given IDs.
     */
    List<MenuItem> findByIdIn(List<Long> ids);

    /**
     * Retrieves a paginated list of menu items by their menu identifiers.
     *
     * @param menuId The menu ID.
     * @return List of {@link MenuItem} corresponding to the given IDs.
     */
    Page<MenuItem> findAllByMenuId(Long menuId, Pageable pageable);

}
