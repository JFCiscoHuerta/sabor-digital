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

/**
 * Implementation of {@link IMenuService} that provides CRUD operations for menus.
 * This service interacts with the {@link IMenuRepository} to manage menu data.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
@Service
public class MenuServiceImpl  implements IMenuService {

    private final IMenuRepository menuRepository;
    private final IMapper mapper;

    /**
     * Constructs a new {@code MenuServiceImpl} with the specified repository and mapper.
     *
     * @param menuRepository The repository for managing menu persistence.
     * @param mapper The mapper for converting between DTOs and entities.
     */
    public MenuServiceImpl(IMenuRepository menuRepository, IMapper mapper) {
        this.menuRepository = menuRepository;
        this.mapper = mapper;
    }

    /**
     * Retrieves a paginated list of menus.
     *
     * @param pageable The pagination information.
     * @return A {@link Page} containing {@link Menu} entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Menu> findAll(Pageable pageable) {
        return menuRepository.findAll(pageable);
    }

    /**
     * Finds a menu by its unique identifier.
     *
     * @param id The unique identifier of the menu.
     * @return The found {@link Menu} entity.
     * @throws ElementNotFoundException If no menu is found with the given ID.
     */
    @Override
    @Transactional(readOnly = true)
    public Menu findById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Menu with id not found"));
    }

    /**
     * Saves a new menu entity based on the provided DTO.
     *
     * @param menuDto The DTO containing menu details.
     * @return The saved {@link Menu} entity.
     * @throws ServiceException If an error occurs during the saving process.
     */
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

    /**
     * Updates an existing menu entity with the provided DTO.
     *
     * @param id The unique identifier of the menu to update.
     * @param menuDto The DTO containing updated menu details.
     * @return The updated {@link Menu} entity.
     * @throws ElementNotFoundException If no menu is found with the given ID.
     * @throws ServiceException If an error occurs during the update process.
     */
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

    /**
     * Deletes a menu entity by its unique identifier.
     *
     * @param id The unique identifier of the menu to delete.
     * @throws ElementNotFoundException If no menu is found with the given ID.
     * @throws ServiceException If an error occurs during the deletion process.
     */
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
