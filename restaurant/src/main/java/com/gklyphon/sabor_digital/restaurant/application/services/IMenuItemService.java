package com.gklyphon.sabor_digital.restaurant.application.services;

import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuItemDto;
import com.gklyphon.sabor_digital.restaurant.domain.entities.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMenuItemService extends IService<MenuItem, MenuItemDto> {

    Page<MenuItem> findAll(Pageable pageable);

}
