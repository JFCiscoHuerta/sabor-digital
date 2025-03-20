package com.gklyphon.sabor_digital.order.infrastructure.feign.clients;

import com.gklyphon.sabor_digital.order.application.dtos.MenuItemDto;
import com.gklyphon.sabor_digital.order.application.dtos.RestaurantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    /**
     * Retrieves a list of menu items based on the provided list of IDs.
     *
     * @param ids List of menu item IDs to fetch.
     * @return List of {@link MenuItemDto} corresponding to the given IDs.
     */
    @GetMapping("/api/menu-items/by-ids")
    List<MenuItemDto> getByIdIn(@RequestParam("ids") List<Long> ids);
}
