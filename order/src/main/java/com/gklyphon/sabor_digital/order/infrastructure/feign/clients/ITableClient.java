package com.gklyphon.sabor_digital.order.infrastructure.feign.clients;

import com.gklyphon.sabor_digital.order.application.dtos.TableDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "table-service", url = "http://localhost:8083")
public interface ITableClient {
    @GetMapping("/api/tables/{id}")
    TableDto getTableById(@PathVariable Long id);
}
