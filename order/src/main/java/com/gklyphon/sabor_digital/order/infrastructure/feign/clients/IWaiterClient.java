package com.gklyphon.sabor_digital.order.infrastructure.feign.clients;

import com.gklyphon.sabor_digital.order.application.dtos.WaiterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "waiter-service", url = "${waiter-service.url}")
public interface IWaiterClient {
    @GetMapping("/api/waiters/{id}")
    WaiterDto getWaiterById(@PathVariable Long id);
}
