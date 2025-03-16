package com.gklyphon.sabor_digital.table.infrastructure.feign.client;

import com.gklyphon.sabor_digital.table.application.dtos.WaiterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "waiter-service", url = "${waiter-service.url}")
public interface IWaiterClient {
    @GetMapping("/api/waiters/by-ids")
    List<WaiterDto> getWaitersByIds(@RequestParam("ids") List<Long> ids);
}
