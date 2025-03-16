package com.gklyphon.sabor_digital.table.infrastructure.repositories;

import com.gklyphon.sabor_digital.table.domain.models.Table;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITableRepository extends JpaRepository<Table, Long> {
    Page<Table> findAllByRestaurantId(Long restaurantId, Pageable pageable);
    List<Table> findByIdIn(List<Long> ids);
}
