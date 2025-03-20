package com.gklyphon.sabor_digital.restaurant.application.services;

import com.gklyphon.sabor_digital.restaurant.application.dtos.RestaurantDto;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Restaurant;

/**
 * Service interface for managing restaurant-related operations.
 * Extends {@link IService} with {@link Restaurant} as the entity type
 * and {@link RestaurantDto} as the DTO type.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
public interface IRestaurantService extends IService<Restaurant, RestaurantDto> {
}
