package com.gklyphon.sabor_digital.table.infrastructure.feign.client;

import com.gklyphon.sabor_digital.table.application.dtos.RestaurantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client for interacting with the Restaurant Service.
 * Provides methods to retrieve restaurant information via REST API.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
@FeignClient(name = "restaurant-service", url = "${restaurant-service.url}")
public interface IRestaurantClient {

    /**
     * Retrieves a restaurant by its ID from the Restaurant Service.
     *
     * @param id The ID of the restaurant to retrieve.
     * @return The {@link RestaurantDto} containing restaurant details.
     */
    @GetMapping("/api/restaurants/{id}")
    RestaurantDto getRestaurantById(@PathVariable Long id);
}
