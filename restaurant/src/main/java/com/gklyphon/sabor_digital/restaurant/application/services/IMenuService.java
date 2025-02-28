package com.gklyphon.sabor_digital.restaurant.application.services;

import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuDto;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMenuService extends IService<Menu, MenuDto> {

    Page<Menu> findAll(Pageable pageable);

}
