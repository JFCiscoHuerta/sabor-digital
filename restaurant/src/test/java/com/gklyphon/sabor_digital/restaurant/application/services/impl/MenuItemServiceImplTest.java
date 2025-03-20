package com.gklyphon.sabor_digital.restaurant.application.services.impl;

import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuItemDto;
import com.gklyphon.sabor_digital.restaurant.application.mapper.IMapper;
import com.gklyphon.sabor_digital.restaurant.domain.entities.MenuItem;
import com.gklyphon.sabor_digital.restaurant.infrastructure.repositories.IMenuItemRepository;
import com.gklyphon.sabor_digital.restaurant.infrastructure.exception.exceptions.ElementNotFoundException;
import com.gklyphon.sabor_digital.restaurant.utils.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuItemServiceImplTest {

    @Mock
    private IMenuItemRepository menuItemRepository;

    @Mock
    private IMapper mapper;

    @InjectMocks
    private MenuItemServiceImpl menuItemService;

    @Test
    void findAll_shouldReturnPageOfMenuItems() {
        Page<MenuItem> menuItemPage = new PageImpl<>(List.of(TestData.MENU_ITEM));
        when(menuItemRepository.findAll(any(Pageable.class))).thenReturn(menuItemPage);

        Page<MenuItem> result = menuItemService.findAll(Pageable.unpaged());

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void findById_shouldReturnMenuItem_whenExists() {
        when(menuItemRepository.findById(anyLong())).thenReturn(Optional.of(TestData.MENU_ITEM));

        MenuItem result = menuItemService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {
        when(menuItemRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ElementNotFoundException.class, () -> menuItemService.findById(1L));
    }

    @Test
    void save_shouldReturnSavedMenuItem() {
        when(mapper.fromMenuItemDtoToMenuItem(any(MenuItemDto.class))).thenReturn(TestData.MENU_ITEM);
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(TestData.MENU_ITEM);

        MenuItem result = menuItemService.save(TestData.MENU_ITEM_DTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void update_shouldReturnUpdatedMenuItem() {
        when(menuItemRepository.findById(anyLong())).thenReturn(Optional.of(TestData.MENU_ITEM));
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(TestData.MENU_ITEM);

        MenuItem result = menuItemService.update(1L, TestData.MENU_ITEM_DTO);

        assertNotNull(result);
    }

    @Test
    void deleteById_shouldDeleteMenuItem_whenExists() {
        when(menuItemRepository.findById(anyLong())).thenReturn(Optional.of(TestData.MENU_ITEM));
        doNothing().when(menuItemRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> menuItemService.deleteById(1L));
        verify(menuItemRepository, times(1)).deleteById(1L);
    }
}
