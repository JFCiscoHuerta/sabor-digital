package com.gklyphon.sabor_digital.waiter.infrastructure.feing.clients;

import com.gklyphon.sabor_digital.waiter.application.dto.TableDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "table-service", url = "http://localhost:8083")
public interface ITableClient {
    @GetMapping("/api/tables/by-ids")
    List<TableDto> getTablesByIdIn(@RequestParam("ids") List<Long> ids);
}
