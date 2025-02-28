package com.gklyphon.sabor_digital.restaurant.domain.repositories;

import com.gklyphon.sabor_digital.restaurant.domain.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMenuRepository extends JpaRepository<Menu, Long> {
}
