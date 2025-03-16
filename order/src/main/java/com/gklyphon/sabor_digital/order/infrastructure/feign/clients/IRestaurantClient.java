package com.gklyphon.sabor_digital.order.infrastructure.feign.clients;

import com.gklyphon.sabor_digital.order.application.dtos.RestaurantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "restaurant-service", url = "${restaurant-service.url}")
public interface IRestaurantClient {

    @GetMapping("/api/restaurants/{id}")
    RestaurantDto getRestaurantById(@PathVariable Long id);

}
