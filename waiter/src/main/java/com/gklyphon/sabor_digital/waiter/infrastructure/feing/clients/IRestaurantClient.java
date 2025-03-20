package com.gklyphon.sabor_digital.waiter.infrastructure.feing.clients;

import com.gklyphon.sabor_digital.waiter.application.dto.RestaurantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign Client interface for interacting with the Restaurant Service.
 * Provides methods to fetch restaurant details from the external service.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
@FeignClient(name = "restaurant-service", url = "${restaurant-service.url}")
public interface IRestaurantClient {

    /**
     * Retrieves a restaurant by its ID.
     *
     * @param id The ID of the restaurant.
     * @return A {@link RestaurantDto} object representing the restaurant.
     */
    @GetMapping("/api/restaurants/{id}")
    RestaurantDto getRestaurantById(@PathVariable Long id);
}
