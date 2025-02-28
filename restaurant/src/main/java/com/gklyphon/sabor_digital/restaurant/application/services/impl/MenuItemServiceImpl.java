package com.gklyphon.sabor_digital.restaurant.application.services.impl;

import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuItemDto;
import com.gklyphon.sabor_digital.restaurant.application.mapper.IMapper;
import com.gklyphon.sabor_digital.restaurant.application.services.IMenuItemService;
import com.gklyphon.sabor_digital.restaurant.domain.entities.MenuItem;
import com.gklyphon.sabor_digital.restaurant.domain.repositories.IMenuItemRepository;
import com.gklyphon.sabor_digital.restaurant.infrastructure.exception.exceptions.ElementNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuItemServiceImpl implements IMenuItemService {

    private final IMenuItemRepository menuItemRepository;
    private final IMapper mapper;

    public MenuItemServiceImpl(IMenuItemRepository menuItemRepository, IMapper mapper) {
        this.menuItemRepository = menuItemRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MenuItem> findAll(Pageable pageable) {
        return menuItemRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public MenuItem findById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Element with id not found."));
    }

    @Override
    @Transactional
    public MenuItem save(MenuItemDto menuItemDto) {
        try {
            return menuItemRepository.save(
                    mapper.fromMenuItemDtoToMenuItem(menuItemDto));
        } catch (Exception ex) {
            throw new ServiceException("", ex);
        }
    }

    @Override
    @Transactional
    public MenuItem update(Long id, MenuItemDto menuItemDto) {
        MenuItem originalMenuItem = findById(id);
        try {
            BeanUtils.copyProperties(menuItemDto, originalMenuItem, "id");
            return menuItemRepository.save(originalMenuItem);
        } catch (Exception ex) {
            throw new ServiceException("", ex);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id);
        try {
            menuItemRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceException("", ex);
        }
    }
}
