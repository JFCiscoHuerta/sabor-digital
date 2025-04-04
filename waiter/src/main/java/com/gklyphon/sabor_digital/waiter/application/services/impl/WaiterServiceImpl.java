package com.gklyphon.sabor_digital.waiter.application.services.impl;

import com.gklyphon.sabor_digital.waiter.application.dto.RestaurantDto;
import com.gklyphon.sabor_digital.waiter.application.dto.TableDto;
import com.gklyphon.sabor_digital.waiter.application.dto.WaiterDto;
import com.gklyphon.sabor_digital.waiter.application.mapper.IMapper;
import com.gklyphon.sabor_digital.waiter.application.services.IWaiterService;
import com.gklyphon.sabor_digital.waiter.domain.models.Waiter;
import com.gklyphon.sabor_digital.waiter.infrastructure.exception.exceptions.ElementNotFoundException;
import com.gklyphon.sabor_digital.waiter.infrastructure.feing.clients.IRestaurantClient;
import com.gklyphon.sabor_digital.waiter.infrastructure.feing.clients.ITableClient;
import com.gklyphon.sabor_digital.waiter.infrastructure.repositories.IWaiterRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service implementation for managing waiters.
 * Provides CRUD operations and additional business logic related to tables.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
@Service
public class WaiterServiceImpl implements IWaiterService {

    private final IWaiterRepository waiterRepository;
    private final IMapper mapper;
    private final IRestaurantClient restaurantClient;
    private final ITableClient tableClient;

    /**
     * Constructs a new {@code WaiterServiceImpl} with required dependencies.
     *
     * @param waiterRepository Repository for waiter entities.
     * @param mapper Mapper for converting DTOs to entities and vice versa.
     * @param restaurantClient Client for fetching restaurant data.
     * @param tableClient Client for fetching table data.
     */
    public WaiterServiceImpl(IWaiterRepository waiterRepository, IMapper mapper, IRestaurantClient restaurantClient, ITableClient tableClient) {
        this.waiterRepository = waiterRepository;
        this.mapper = mapper;
        this.restaurantClient = restaurantClient;
        this.tableClient = tableClient;
    }

    /**
     * Finds a waiter by ID.
     *
     * @param id The ID of the waiter.
     * @return The found waiter.
     * @throws ElementNotFoundException if no waiter is found.
     */
    @Override
    @Transactional(readOnly = true)
    public Waiter findById(Long id) {
        return waiterRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Waiter not found."));
    }

    /**
     * Retrieves a paginated list of waiters for a given restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @param pageable Pagination information.
     * @return A paginated list of waiters.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Waiter> findAllByRestaurantId(Long restaurantId, Pageable pageable) {
        return waiterRepository.findAllByRestaurantId(restaurantId, pageable);
    }

    /**
     * Retrieves a paginated list of all waiters.
     *
     * @param pageable Pagination information.
     * @return A paginated list of waiters.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Waiter> findAll(Pageable pageable) {
        return waiterRepository.findAll(pageable);
    }

    /**
     * Saves a new waiter.
     *
     * @param waiterDto The DTO containing waiter data.
     * @return The saved waiter.
     */
    @Override
    @Transactional
    public Waiter save(WaiterDto waiterDto) {
        verifyClientResponses(waiterDto);
        return waiterRepository.save(mapper.fromWaiterDtoToWaiter(waiterDto));
    }

    /**
     * Updates an existing waiter.
     *
     * @param id The ID of the waiter to update.
     * @param waiterDto The DTO containing updated waiter data.
     * @return The updated waiter.
     * @throws ServiceException if an error occurs during update.
     */
    @Override
    @Transactional
    public Waiter update(Long id, WaiterDto waiterDto) {
        Waiter originalWaiter = findById(id);
        verifyClientResponses(waiterDto);
        try {
            BeanUtils.copyProperties(waiterDto, originalWaiter, "id");
            return waiterRepository.save(originalWaiter);
        } catch (Exception ex) {
            throw new ServiceException("Error updating waiter",ex);
        }
    }

    /**
     * Deletes a waiter by ID.
     *
     * @param id The ID of the waiter to delete.
     * @throws ServiceException if an error occurs during deletion.
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id);
        try {
            waiterRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceException("Error deleting waiter", ex);
        }
    }

    /**
     * Retrieves a list of waiters by their IDs.
     *
     * @param ids The list of waiter IDs.
     * @return A list of found waiters.
     * @throws ElementNotFoundException if some IDs do not match any waiter.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Waiter> findByIdIn(List<Long> ids) {
        List<Waiter> waiters = waiterRepository.findByIdIn(ids);

        List<Long> foundIds = waiters.stream()
                .map(Waiter::getId)
                .toList();

        List<Long> missingIds = ids
                .stream()
                .filter(id -> !foundIds.contains(id))
                .toList();
        if (!missingIds.isEmpty()) {
            throw new ElementNotFoundException("No waiters were found for the provided IDs");
        }

        return waiters;
    }

    /**
     * Verifies the existence of related restaurant and table IDs before saving or updating a waiter.
     *
     * @param waiterDto The waiter DTO containing restaurant and table IDs.
     * @throws ElementNotFoundException if restaurant or tables do not exist.
     * @throws ServiceException if an error occurs during verification.
     */
    @Transactional(readOnly = true)
    void verifyClientResponses(WaiterDto waiterDto) {

        try {
            RestaurantDto restaurantDto = restaurantClient.getRestaurantById(waiterDto.getRestaurantId());
            List<TableDto> tableDtoList = tableClient.getTablesByIdIn(waiterDto.getTablesId());

            if ( ( !waiterDto.getTablesId().isEmpty() && tableDtoList.isEmpty()) || restaurantDto == null ) {
                throw new ElementNotFoundException("Invalid restaurant or table id.");
            }
        } catch (Exception ex) {
            throw new ServiceException("Error fetching waiters data.", ex);
        }

    }

}
