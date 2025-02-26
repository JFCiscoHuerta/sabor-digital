package com.gklyphon.sabor_digital.restaurant.domain.repositories;

import com.gklyphon.sabor_digital.restaurant.domain.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {
}
