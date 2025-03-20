package com.gklyphon.sabor_digital.table.infrastructure.feign.client;

import com.gklyphon.sabor_digital.table.application.dtos.WaiterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Feign client for interacting with the Waiter Service.
 * Provides methods to retrieve waiter information via REST API.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
@FeignClient(name = "waiter-service", url = "${waiter-service.url}")
public interface IWaiterClient {

    /**
     * Retrieves a list of waiters by their IDs from the Waiter Service.
     *
     * @param ids The list of waiter IDs to retrieve.
     * @return A list of {@link WaiterDto} containing waiter details.
     */
    @GetMapping("/api/waiters/by-ids")
    List<WaiterDto> getWaitersByIds(@RequestParam("ids") List<Long> ids);
}
