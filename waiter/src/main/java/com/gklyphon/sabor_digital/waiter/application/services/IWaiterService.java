package com.gklyphon.sabor_digital.waiter.application.services;

import com.gklyphon.sabor_digital.waiter.application.dto.WaiterDto;
import com.gklyphon.sabor_digital.waiter.domain.models.Waiter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IWaiterService extends IService<Waiter, WaiterDto> {
    Page<Waiter> findAllByRestaurantId(Long restaurantId, Pageable pageable);
    List<Waiter> findByIdIn(List<Long> ids);
}
