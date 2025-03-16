package com.gklyphon.sabor_digital.table.application.services;

import com.gklyphon.sabor_digital.table.application.dtos.TableDto;
import com.gklyphon.sabor_digital.table.domain.models.Table;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITableService extends IService<Table, TableDto> {
    Page<Table> findAllByRestaurantId(Long restaurantId, Pageable pageable);
    List<Table> findByIdIn(List<Long> ids);
}
