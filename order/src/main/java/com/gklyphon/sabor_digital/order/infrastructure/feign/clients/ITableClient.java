package com.gklyphon.sabor_digital.order.infrastructure.feign.clients;

import com.gklyphon.sabor_digital.order.application.dtos.TableDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client for interacting with the table-service.
 *
 * <p>Provides methods to retrieve table information from the external table service.</p>
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
@FeignClient(name = "table-service", url = "http://localhost:8083")
public interface ITableClient {

    /**
     * Retrieves a table by their ID.
     *
     * @param id the ID of the table to retrieve
     * @return the corresponding TableDto object
     */
    @GetMapping("/api/tables/{id}")
    TableDto getTableById(@PathVariable Long id);
}
