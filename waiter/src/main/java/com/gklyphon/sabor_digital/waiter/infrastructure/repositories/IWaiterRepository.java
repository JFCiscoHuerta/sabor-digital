package com.gklyphon.sabor_digital.waiter.infrastructure.repositories;

import com.gklyphon.sabor_digital.waiter.domain.models.Waiter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWaiterRepository extends JpaRepository<Waiter, Long> {
    Page<Waiter> findAllByRestaurantId(Long id, Pageable pageable);
}
