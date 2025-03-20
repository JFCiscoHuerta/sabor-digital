package com.gklyphon.sabor_digital.restaurant.application.services.impl;

import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuItemDto;
import com.gklyphon.sabor_digital.restaurant.application.mapper.IMapper;
import com.gklyphon.sabor_digital.restaurant.application.services.IMenuItemService;
import com.gklyphon.sabor_digital.restaurant.domain.entities.MenuItem;
import com.gklyphon.sabor_digital.restaurant.infrastructure.repositories.IMenuItemRepository;
import com.gklyphon.sabor_digital.restaurant.infrastructure.exception.exceptions.ElementNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@link IMenuItemService} that provides CRUD operations for menu items.
 * This service interacts with the {@link IMenuItemRepository} to manage menu item data.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
@Service
public class MenuItemServiceImpl implements IMenuItemService {

    private final IMenuItemRepository menuItemRepository;
    private final IMapper mapper;

    /**
     * Constructs a new {@code MenuItemServiceImpl} with the specified repository and mapper.
     *
     * @param menuItemRepository The repository for managing menu item persistence.
     * @param mapper The mapper for converting between DTOs and entities.
     */
    public MenuItemServiceImpl(IMenuItemRepository menuItemRepository, IMapper mapper) {
        this.menuItemRepository = menuItemRepository;
        this.mapper = mapper;
    }

    /**
     * Retrieves a paginated list of menu items.
     *
     * @param pageable The pagination information.
     * @return A {@link Page} containing {@link MenuItem} entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MenuItem> findAll(Pageable pageable) {
        return menuItemRepository.findAll(pageable);
    }

    /**
     * Finds a menu item by its unique identifier.
     *
     * @param id The unique identifier of the menu item.
     * @return The found {@link MenuItem} entity.
     * @throws ElementNotFoundException If no menu item is found with the given ID.
     */
    @Override
    @Transactional(readOnly = true)
    public MenuItem findById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Element with id not found."));
    }

    /**
     * Saves a new menu item entity based on the provided DTO.
     *
     * @param menuItemDto The DTO containing menu item details.
     * @return The saved {@link MenuItem} entity.
     * @throws ServiceException If an error occurs during the saving process.
     */
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

    /**
     * Updates an existing menu item entity with the provided DTO.
     *
     * @param id The unique identifier of the menu item to update.
     * @param menuItemDto The DTO containing updated menu item details.
     * @return The updated {@link MenuItem} entity.
     * @throws ElementNotFoundException If no menu item is found with the given ID.
     * @throws ServiceException If an error occurs during the update process.
     */
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

    /**
     * Deletes a menu item entity by its unique identifier.
     *
     * @param id The unique identifier of the menu item to delete.
     * @throws ElementNotFoundException If no menu item is found with the given ID.
     * @throws ServiceException If an error occurs during the deletion process.
     */
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

    /**
     * Retrieves a list of menu items by their unique identifiers.
     *
     * @param ids List of menu item IDs to retrieve.
     * @return List of {@link MenuItem} corresponding to the given IDs.
     * @throws ElementNotFoundException If any of the provided IDs do not match existing menu items.
     */
    @Override
    @Transactional(readOnly = true)
    public List<MenuItem> findByIdIn(List<Long> ids) {
        List<MenuItem> menuItems = menuItemRepository.findByIdIn(ids);
        List<Long> foundIds = menuItems
                .stream()
                .map(MenuItem::getId)
                .toList();
        List<Long> missingIds = ids
                .stream()
                .filter(id -> !foundIds.contains(id))
                .toList();
        if (!missingIds.isEmpty()) {
            throw new ElementNotFoundException("No menu items were found for the provided IDs.");
        }
        return menuItems;
    }
}
