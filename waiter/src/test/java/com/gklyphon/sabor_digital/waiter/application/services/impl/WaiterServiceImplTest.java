package com.gklyphon.sabor_digital.waiter.application.services.impl;

import com.gklyphon.sabor_digital.waiter.TestData;
import com.gklyphon.sabor_digital.waiter.application.dto.WaiterDto;
import com.gklyphon.sabor_digital.waiter.application.mapper.IMapper;
import com.gklyphon.sabor_digital.waiter.domain.models.Waiter;
import com.gklyphon.sabor_digital.waiter.infrastructure.exception.exceptions.ElementNotFoundException;
import com.gklyphon.sabor_digital.waiter.infrastructure.repositories.IWaiterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WaiterServiceImplTest {

    @Mock
    private IWaiterRepository waiterRepository;

    @Mock
    private IMapper mapper;

    @InjectMocks
    private WaiterServiceImpl waiterService;


    @Test
    void findById_shouldReturnWaiter_whenExists() {
        when(waiterRepository.findById(1L)).thenReturn(Optional.of(TestData.WAITER));
        Waiter found = waiterService.findById(1L);
        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {
        when(waiterRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> waiterService.findById(1L));
    }

    @Test
    void findAllByRestaurantId_shouldReturnPage() {
        when(waiterRepository.findAllByRestaurantId(anyLong(), any(Pageable.class))).thenReturn(TestData.WAITER_PAGE);
        Page<Waiter> page = waiterService.findAllByRestaurantId(1L, mock(Pageable.class));
        assertFalse(page.isEmpty());
    }

    @Test
    void findAll_shouldReturnPage() {
        when(waiterRepository.findAll(any(Pageable.class))).thenReturn(TestData.WAITER_PAGE);
        Page<Waiter> page = waiterService.findAll(mock(Pageable.class));
        assertFalse(page.isEmpty());
    }

    @Test
    void save_shouldReturnSavedWaiter() {
        when(mapper.fromWaiterDtoToWaiter(any(WaiterDto.class))).thenReturn(TestData.WAITER);
        when(waiterRepository.save(any(Waiter.class))).thenReturn(TestData.WAITER);
        Waiter saved = waiterService.save(TestData.WAITER_DTO);
        assertNotNull(saved);
        assertEquals(TestData.WAITER.getEmail(), saved.getEmail());
    }

    @Test
    void update_shouldReturnUpdatedWaiter() {
        when(waiterRepository.findById(1L)).thenReturn(Optional.of(TestData.WAITER));
        when(waiterRepository.save(any(Waiter.class))).thenReturn(TestData.WAITER);
        Waiter updated = waiterService.update(1L, TestData.WAITER_DTO);
        assertNotNull(updated);
        assertEquals(TestData.WAITER_DTO.getEmail(), updated.getEmail());
    }

    @Test
    void deleteById_shouldDeleteWhenExists() {
        when(waiterRepository.findById(1L)).thenReturn(Optional.of(TestData.WAITER));
        doNothing().when(waiterRepository).deleteById(1L);
        assertDoesNotThrow(() -> waiterService.deleteById(1L));
        verify(waiterRepository, times(1)).deleteById(1L);
    }

}