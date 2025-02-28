package com.gklyphon.sabor_digital.restaurant.application.services.impl;

import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuDto;
import com.gklyphon.sabor_digital.restaurant.application.mapper.IMapper;
import com.gklyphon.sabor_digital.restaurant.application.services.IMenuService;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Menu;
import com.gklyphon.sabor_digital.restaurant.domain.repositories.IMenuRepository;
import com.gklyphon.sabor_digital.restaurant.infrastructure.exception.exceptions.ElementNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuServiceImpl  implements IMenuService {

    private final IMenuRepository menuRepository;
    private final IMapper mapper;

    public MenuServiceImpl(IMenuRepository menuRepository, IMapper mapper) {
        this.menuRepository = menuRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Menu> findAll(Pageable pageable) {
        return menuRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Menu findById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Menu with id not found"));
    }

    @Override
    @Transactional
    public Menu save(MenuDto menuDto) {
        try {
            return menuRepository.save(
                    mapper.fromMenuDtoToMenu(menuDto));
        } catch (Exception ex) {
            throw new ServiceException("", ex);
        }
    }

    @Override
    @Transactional
    public Menu update(Long id, MenuDto menuDto) {
        try {
            Menu originalMenu = findById(id);
            BeanUtils.copyProperties(menuDto, originalMenu, "id");
            return menuRepository.save(originalMenu);
        } catch (Exception ex) {
            throw new ServiceException("", ex);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id);
        try {
            menuRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceException("", ex);
        }
    }
}
