package com.gklyphon.sabor_digital.restaurant.infrastructure.repositories;

import com.gklyphon.sabor_digital.restaurant.domain.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Menu} entities.
 * This interface extends {@link JpaRepository} to provide basic CRUD operations.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
public interface IMenuRepository extends JpaRepository<Menu, Long> {
}
