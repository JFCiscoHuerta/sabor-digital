package com.gklyphon.sabor_digital.order.infrastructure.feign.clients;

import com.gklyphon.sabor_digital.order.application.dtos.RestaurantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client for interact with the restaurant-service
 *
 * <p>Provides methods to retrieve restaurant information from the external restaurant service.</p>
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
@FeignClient(name = "restaurant-service", url = "${restaurant-service.url}")
public interface IRestaurantClient {

    /**
     * Retrieves a restaurant by its ID.
     *
     * @param id the ID of the restaurant to retrieve
     * @return the corresponding RestaurantDto object
     */
    @GetMapping("/api/restaurants/{id}")
    RestaurantDto getRestaurantById(@PathVariable Long id);

}
