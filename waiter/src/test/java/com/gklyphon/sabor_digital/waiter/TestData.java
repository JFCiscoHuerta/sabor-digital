package com.gklyphon.sabor_digital.waiter;

import com.gklyphon.sabor_digital.waiter.application.dto.WaiterDto;
import com.gklyphon.sabor_digital.waiter.domain.models.Waiter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class TestData {

    public static final Waiter WAITER = new Waiter.Builder()
            .id(1L)
            .email("waiter@gmail.com")
            .firstname("firstname")
            .lastname("lastname")
            .restaurantId(1L)
            .tablesId(List.of(1L, 2L))
            .build();

    public static final WaiterDto WAITER_DTO = new WaiterDto.Builder()
            .email("waiter@gmail.com")
            .firstname("firstname")
            .lastname("lastname")
            .restaurantId(1L)
            .tablesId(List.of(1L, 2L))
            .build();

    public static final Page<Waiter> WAITER_PAGE = new PageImpl<>(List.of(WAITER));

}
