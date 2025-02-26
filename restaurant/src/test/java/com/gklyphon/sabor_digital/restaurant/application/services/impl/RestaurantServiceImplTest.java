package com.gklyphon.sabor_digital.restaurant.application.services.impl;

import com.gklyphon.sabor_digital.restaurant.application.mapper.IMapper;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Restaurant;
import com.gklyphon.sabor_digital.restaurant.domain.repositories.IRestaurantRepository;
import com.gklyphon.sabor_digital.restaurant.infrastructure.exception.exceptions.ElementNotFoundException;
import com.gklyphon.sabor_digital.restaurant.utils.TestData;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceImplTest {

    @Mock
    private IRestaurantRepository restaurantRepository;

    @Mock
    private IMapper mapper;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @Test
    void findById_shouldReturnRestaurant_whenFound() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(TestData.RESTAURANT));
        Restaurant found = restaurantService.findById(1L);
        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> restaurantService.findById(1L));
    }

    @Test
    void save_shouldReturnSavedRestaurant() {
        when(mapper.fromRestaurantDtoToRestaurant(TestData.RESTAURANT_DTO)).thenReturn(TestData.RESTAURANT);
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(TestData.RESTAURANT);
        Restaurant saved = restaurantService.save(TestData.RESTAURANT_DTO);
        assertNotNull(saved);
        assertEquals(1L, saved.getId());
    }

    @Test
    void save_shouldThrowServiceException_onFailure() {
        when(mapper.fromRestaurantDtoToRestaurant(TestData.RESTAURANT_DTO)).thenReturn(TestData.RESTAURANT);
        when(restaurantRepository.save(any(Restaurant.class))).thenThrow(new RuntimeException("DB error"));
        assertThrows(ServiceException.class, () -> restaurantService.save(TestData.RESTAURANT_DTO));
    }

    @Test
    void update_shouldReturnUpdatedRestaurant() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(TestData.RESTAURANT));
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(TestData.RESTAURANT);
        Restaurant updated = restaurantService.update(1L, TestData.RESTAURANT_DTO);
        assertNotNull(updated);
    }

    @Test
    void update_shouldThrowServiceException_onFailure() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(TestData.RESTAURANT));
        when(restaurantRepository.save(any(Restaurant.class))).thenThrow(new RuntimeException("DB error"));
        assertThrows(ServiceException.class, () -> restaurantService.update(1L, TestData.RESTAURANT_DTO));
    }

    @Test
    void deleteById_shouldDeleteRestaurant() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(TestData.RESTAURANT));
        doNothing().when(restaurantRepository).deleteById(1L);
        assertDoesNotThrow(() -> restaurantService.deleteById(1L));
        verify(restaurantRepository).deleteById(1L);
    }

    @Test
    void deleteById_shouldThrowServiceException_onFailure() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(TestData.RESTAURANT));
        doThrow(new RuntimeException("DB error")).when(restaurantRepository).deleteById(1L);
        assertThrows(ServiceException.class, () -> restaurantService.deleteById(1L));
    }
}