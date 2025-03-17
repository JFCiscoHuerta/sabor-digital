package com.gklyphon.sabor_digital.order.infrastructure.feign.clients;

import com.gklyphon.sabor_digital.order.application.dtos.WaiterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client for interacting with the waiter-service.
 *
 * <p>Provides methods to retrieve waiter information from the external waiter service.</p>
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
@FeignClient(name = "waiter-service", url = "${waiter-service.url}")
public interface IWaiterClient {

    /**
     * Retrieves a waiter by their ID.
     *
     * @param id the ID of the waiter to retrieve
     * @return the corresponding WaiterDto object
     */
    @GetMapping("/api/waiters/{id}")
    WaiterDto getWaiterById(@PathVariable Long id);
}
