package com.gklyphon.sabor_digital.order.application.services.impl;

import com.gklyphon.sabor_digital.order.application.dtos.*;
import com.gklyphon.sabor_digital.order.application.mapper.IMapper;
import com.gklyphon.sabor_digital.order.application.services.IOrderService;
import com.gklyphon.sabor_digital.order.domain.models.Order;
import com.gklyphon.sabor_digital.order.infrastructure.exception.exceptions.ElementNotFoundException;
import com.gklyphon.sabor_digital.order.infrastructure.feign.clients.IRestaurantClient;
import com.gklyphon.sabor_digital.order.infrastructure.feign.clients.ITableClient;
import com.gklyphon.sabor_digital.order.infrastructure.feign.clients.IWaiterClient;
import com.gklyphon.sabor_digital.order.infrastructure.repositories.IOrderRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service implementation for managing orders, providing CRUD operations.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
@Service
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IMapper mapper;

    private final IRestaurantClient restaurantClient;
    private final ITableClient tableClient;
    private final IWaiterClient waiterClient;

    /**
     * Constructs an OrderServiceImpl with the necessary dependencies
     *
     * @param orderRepository the repository for order data persistence
     * @param mapper the mapper for converting between DTOs and entities
     * @param restaurantClient client for fetching restaurant data
     * @param tableClient client for fetching table data
     * @param waiterClient client for fetching waiter data
     */
    public OrderServiceImpl(IOrderRepository orderRepository, IMapper mapper, IRestaurantClient restaurantClient, ITableClient tableClient, IWaiterClient waiterClient) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
        this.restaurantClient = restaurantClient;
        this.tableClient = tableClient;
        this.waiterClient = waiterClient;
    }

    /**
     * Retrieves all orders for a given restaurant ID with pagination.
     *
     * @param restaurantId the ID of the restaurant
     * @param pageable the pagination information
     * @return a page of orders
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Order> findAllByRestaurantId(Long restaurantId, Pageable pageable) {
        return orderRepository.findAllByRestaurantId(restaurantId, pageable);
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id the ID of the entity
     * @return the found order
     * @throws ElementNotFoundException if no order is found with the given ID
     */
    @Override
    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Order not found"));
    }

    /**
     * Saves a new order.
     *
     * @param orderDto the DTO representing the new entity
     * @return the saved order
     * @throws ServiceException if an error occurs during the save process
     */
    @Override
    @Transactional
    public Order save(OrderDto orderDto) {
        verifyClientResponses(orderDto);
        try {
            return orderRepository.save(mapper.fromOrderDtoToOrder(orderDto));
        } catch (Exception ex) {
            throw new ServiceException("An error occurred while saving the order", ex);
        }
    }

    /**
     * Updates an existent order by its ID.
     *
     * @param id the ID of the entity to update
     * @param orderDto the DTO containing the updated data
     * @return the updated order
     * @throws ServiceException if an error occurs during the update process.
     */
    @Override
    @Transactional
    public Order update(Long id, OrderDto orderDto) {
        Order originalOrder = findById(id);
        verifyClientResponses(orderDto);
        try {
            BeanUtils.copyProperties(orderDto, originalOrder, "id");
            return orderRepository.save(originalOrder);
        } catch (Exception ex) {
            throw new ServiceException("An error occurred while updating the order", ex);
        }
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id the id of the entity to delete
     * @throws ServiceException if an error occurs during the deletion process.
     */
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

    /**
     * Verifies the existence of restaurant, table & waiter associated with the order.
     *
     * @param orderDto
     * @throws ElementNotFoundException if any of the associated entities are not found
     * @throws ServiceException if an error occurs during the verification process
     */
    @Transactional(readOnly = true)
    void verifyClientResponses(OrderDto orderDto) {
        try {
            RestaurantDto restaurantDto = restaurantClient.getRestaurantById(orderDto.getRestaurantId());
            List<MenuItemDto> menuItems = restaurantClient.getByIdIn(orderDto.getItemsId());
            TableDto tableDto = tableClient.getTableById(orderDto.getTableId());
            WaiterDto waiterDto = waiterClient.getWaiterById(orderDto.getWaiterId());

            if ( (!orderDto.getItemsId().isEmpty() && menuItems.isEmpty()) || restaurantDto == null || tableDto == null || waiterDto == null) {
                throw new ElementNotFoundException("Invalid restaurant, table, or waiter ID");
            }
        } catch (Exception ex) {
            throw new ServiceException("Error fetching restaurant, table, or waiter data", ex);
        }
    }
}
