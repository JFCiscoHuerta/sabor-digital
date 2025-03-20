package com.gklyphon.sabor_digital.waiter.infrastructure.feing.clients;

import com.gklyphon.sabor_digital.waiter.application.dto.TableDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Feign Client interface for interacting with the Table Service.
 * Provides methods to fetch table details from the external service.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
@FeignClient(name = "table-service", url = "http://localhost:8083")
public interface ITableClient {

    /**
     * Retrieves a list of tables based on their IDs.
     *
     * @param ids The list of table IDs.
     * @return A list of {@link TableDto} objects representing the requested tables.
     */
    @GetMapping("/api/tables/by-ids")
    List<TableDto> getTablesByIdIn(@RequestParam("ids") List<Long> ids);
}
