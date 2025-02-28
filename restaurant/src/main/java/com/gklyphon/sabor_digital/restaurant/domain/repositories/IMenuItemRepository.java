package com.gklyphon.sabor_digital.restaurant.domain.repositories;

import com.gklyphon.sabor_digital.restaurant.domain.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMenuItemRepository extends JpaRepository<MenuItem, Long> {
}
