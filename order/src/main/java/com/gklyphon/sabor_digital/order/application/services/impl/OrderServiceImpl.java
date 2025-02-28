package com.gklyphon.sabor_digital.order.application.services.impl;

import com.gklyphon.sabor_digital.order.application.dtos.OrderDto;
import com.gklyphon.sabor_digital.order.application.mapper.IMapper;
import com.gklyphon.sabor_digital.order.application.services.IOrderService;
import com.gklyphon.sabor_digital.order.domain.models.Order;
import com.gklyphon.sabor_digital.order.infrastructure.exception.exceptions.ElementNotFoundException;
import com.gklyphon.sabor_digital.order.infrastructure.repositories.IOrderRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IMapper mapper;

    public OrderServiceImpl(IOrderRepository orderRepository, IMapper mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Order> findAllByRestaurantId(Long restaurantId, Pageable pageable) {
        return orderRepository.findAllByRestaurantId(restaurantId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Order not found"));
    }

    @Override
    @Transactional
    public Order save(OrderDto orderDto) {
        try {
            return orderRepository.save(mapper.fromOrderDtoToOrder(orderDto));
        } catch (Exception ex) {
            throw new ServiceException("An error occurred while saving the order", ex);
        }
    }

    @Override
    @Transactional
    public Order update(Long id, OrderDto orderDto) {
        Order originalOrder = findById(id);
        try {
            BeanUtils.copyProperties(orderDto, originalOrder, "id");
            return orderRepository.save(originalOrder);
        } catch (Exception ex) {
            throw new ServiceException("An error occurred while updating the order", ex);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id);
        try {
            orderRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceException("An error occurred while deleting the order", ex);
        }
    }
}
