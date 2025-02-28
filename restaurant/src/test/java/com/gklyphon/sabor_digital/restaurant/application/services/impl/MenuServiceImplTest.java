package com.gklyphon.sabor_digital.restaurant.application.services.impl;

import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuDto;
import com.gklyphon.sabor_digital.restaurant.application.mapper.IMapper;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Menu;
import com.gklyphon.sabor_digital.restaurant.domain.repositories.IMenuRepository;
import com.gklyphon.sabor_digital.restaurant.infrastructure.exception.exceptions.ElementNotFoundException;
import com.gklyphon.sabor_digital.restaurant.utils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuServiceImplTest {

    @Mock
    private IMenuRepository menuRepository;

    @Mock
    private IMapper mapper;

    @InjectMocks
    private MenuServiceImpl menuService;

    @Test
    void findAll_shouldReturnPagedMenus() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Menu> menuPage = new PageImpl<>(Collections.singletonList(TestData.MENU));
        when(menuRepository.findAll(pageable)).thenReturn(menuPage);

        Page<Menu> result = menuService.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(menuRepository, times(1)).findAll(pageable);
    }

    @Test
    void findById_shouldReturnMenu_whenFound() {
        when(menuRepository.findById(anyLong())).thenReturn(Optional.of(TestData.MENU));
        Menu menu = menuService.findById(1L);

        assertNotNull(menu);
        assertEquals(TestData.MENU.getId(), menu.getId());
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {
        when(menuRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> menuService.findById(1L));
    }

    @Test
    void save_shouldReturnSavedMenu() {
        when(menuRepository.save(any(Menu.class))).thenReturn(TestData.MENU);
        when(mapper.fromMenuDtoToMenu(any(MenuDto.class))).thenReturn(TestData.MENU);
        Menu savedMenu = menuService.save(TestData.MENU_DTO);

        assertNotNull(savedMenu);
        assertEquals(TestData.MENU.getId(), savedMenu.getId());
        verify(menuRepository, times(1)).save(any(Menu.class));
    }

    @Test
    void update_shouldReturnUpdatedMenu() {
        when(menuRepository.findById(1L)).thenReturn(Optional.of(TestData.MENU));
        when(menuRepository.save(any(Menu.class))).thenReturn(TestData.MENU);

        Menu updatedMenu = menuService.update(1L, TestData.MENU_DTO);

        assertNotNull(updatedMenu);
        assertEquals(TestData.MENU.getId(), updatedMenu.getId());
        verify(menuRepository, times(1)).save(any(Menu.class));
    }

    @Test
    void deleteById_shouldInvokeRepositoryDelete() {
        when(menuRepository.findById(1L)).thenReturn(Optional.of(TestData.MENU));
        doNothing().when(menuRepository).deleteById(1L);

        menuService.deleteById(1L);

        verify(menuRepository, times(1)).deleteById(1L);
    }
}