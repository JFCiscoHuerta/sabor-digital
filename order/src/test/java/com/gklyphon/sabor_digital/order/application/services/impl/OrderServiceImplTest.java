package com.gklyphon.sabor_digital.order.application.services.impl;

import com.gklyphon.sabor_digital.order.TestData;
import com.gklyphon.sabor_digital.order.application.dtos.OrderDto;
import com.gklyphon.sabor_digital.order.application.mapper.IMapper;
import com.gklyphon.sabor_digital.order.domain.models.Order;
import com.gklyphon.sabor_digital.order.infrastructure.exception.exceptions.ElementNotFoundException;
import com.gklyphon.sabor_digital.order.infrastructure.repositories.IOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private IOrderRepository orderRepository;

    @Mock
    private IMapper mapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void testFindById_Success() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(TestData.ORDER));
        Order foundOrder = orderService.findById(1L);
        assertNotNull(foundOrder);
        assertEquals(1L, foundOrder.getId());
    }

    @Test
    void testFindById_NotFound() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> orderService.findById(1L));
    }

    @Test
    void testSave_Success() {
        when(mapper.fromOrderDtoToOrder(any(OrderDto.class))).thenReturn(TestData.ORDER);
        when(orderRepository.save(any(Order.class))).thenReturn(TestData.ORDER);
        Order savedOrder = orderService.save(TestData.ORDER_DTO);
        assertNotNull(savedOrder);
        assertEquals(TestData.ORDER.getId(), savedOrder.getId());
    }

    @Test
    void testUpdate_Success() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(TestData.ORDER));
        when(orderRepository.save(any(Order.class))).thenReturn(TestData.ORDER);
        Order updatedOrder = orderService.update(1L, TestData.ORDER_DTO);
        assertNotNull(updatedOrder);
        assertEquals(TestData.ORDER.getId(), updatedOrder.getId());
    }

    @Test
    void testDeleteById_Success() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(TestData.ORDER));
        doNothing().when(orderRepository).deleteById(1L);
        assertDoesNotThrow(() -> orderService.deleteById(1L));
    }

    @Test
    void testFindAllByRestaurantId_Success() {
        when(orderRepository.findAllByRestaurantId(anyLong(), any(Pageable.class))).thenReturn(TestData.ORDER_PAGE);
        Page<Order> result = orderService.findAllByRestaurantId(1L, mock(Pageable.class));
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }
}
