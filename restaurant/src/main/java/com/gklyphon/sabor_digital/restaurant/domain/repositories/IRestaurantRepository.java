package com.gklyphon.sabor_digital.restaurant.domain.repositories;

import com.gklyphon.sabor_digital.restaurant.domain.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Restaurant} entities.
 * This interface extends {@link JpaRepository} to provide basic CRUD operations.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {
}
