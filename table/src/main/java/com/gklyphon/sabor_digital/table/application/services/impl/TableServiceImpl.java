package com.gklyphon.sabor_digital.table.application.services.impl;

import com.gklyphon.sabor_digital.table.application.dtos.RestaurantDto;
import com.gklyphon.sabor_digital.table.application.dtos.TableDto;
import com.gklyphon.sabor_digital.table.application.dtos.WaiterDto;
import com.gklyphon.sabor_digital.table.application.mapper.IMapper;
import com.gklyphon.sabor_digital.table.application.services.ITableService;
import com.gklyphon.sabor_digital.table.domain.models.Table;
import com.gklyphon.sabor_digital.table.infrastructure.exception.exceptions.ElementNotFoundException;
import com.gklyphon.sabor_digital.table.infrastructure.feign.client.IRestaurantClient;
import com.gklyphon.sabor_digital.table.infrastructure.feign.client.IWaiterClient;
import com.gklyphon.sabor_digital.table.infrastructure.repositories.ITableRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service implementation for managing tables.
 * Provides CRUD operations and additional business logic related to tables.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
@Service
public class TableServiceImpl implements ITableService {

    private final ITableRepository tableRepository;
    private final IMapper iMapper;
    private final IWaiterClient waiterClient;
    private final IRestaurantClient restaurantClient;

    /**
     * Constructs a new {@code TableServiceImpl} with the required dependencies.
     *
     * @param tableRepository Repository for table entity operations.
     * @param iMapper Mapper for converting between DTOs and entities.
     * @param waiterClient Feign client for retrieving waiter information.
     * @param restaurantClient Feign client for retrieving restaurant information.
     */
    public TableServiceImpl(ITableRepository tableRepository, IMapper iMapper, IWaiterClient waiterClient, IRestaurantClient restaurantClient) {
        this.tableRepository = tableRepository;
        this.iMapper = iMapper;
        this.waiterClient = waiterClient;
        this.restaurantClient = restaurantClient;
    }

    /**
     * Retrieves a paginated list of tables for a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @param pageable Pagination information.
     * @return A paginated list of tables.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Table> findAllByRestaurantId(Long restaurantId, Pageable pageable) {
        return tableRepository.findAllByRestaurantId(restaurantId, pageable);
    }

    /**
     * Retrieves a table by its ID.
     *
     * @param id The ID of the table.
     * @return The found table.
     * @throws ElementNotFoundException if the table is not found.
     */
    @Override
    @Transactional(readOnly = true)
    public Table findById(Long id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Table not found."));
    }

    /**
     * Saves a new table.
     *
     * @param tableDto The DTO containing table data.
     * @return The created table.
     * @throws ServiceException if an error occurs while saving.
     */
    @Override
    @Transactional
    public Table save(TableDto tableDto) {
        verifyClientResponses(tableDto);
        try {
            return tableRepository.save(iMapper.fromTableDtoToTable(tableDto));
        } catch (Exception ex) {
            throw new ServiceException("An error occurred while saving the table", ex);
        }
    }

    /**
     * Updates an existing table.
     *
     * @param id The ID of the table to update.
     * @param tableDto The DTO containing updated table data.
     * @return The updated table.
     * @throws ServiceException if an error occurs while updating.
     */
    @Override
    @Transactional
    public Table update(Long id, TableDto tableDto) {
        Table originalTable = findById(id);
        verifyClientResponses(tableDto);
        try {
            BeanUtils.copyProperties(tableDto, originalTable, "id");
            return tableRepository.save(originalTable);
        } catch (Exception ex) {
            throw new ServiceException("An error occurred while updating the table", ex);
        }
    }

    /**
     * Deletes a table by its ID.
     *
     * @param id The ID of the table to delete.
     * @throws ServiceException if an error occurs while deleting.
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id);
        try {
            tableRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceException("An error occurred while deleting the table", ex);
        }
    }

    /**
     * Retrieves a list of tables by their IDs.
     *
     * @param ids The list of table IDs.
     * @return A list of tables.
     * @throws ElementNotFoundException if no tables are found for the provided IDs.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Table> findByIdIn(List<Long> ids) {
        List<Table> tables = tableRepository.findByIdIn(ids);

        List<Long> foundIds = tables
                .stream()
                .map(Table::getId)
                .toList();

        List<Long> missingIds = ids
                .stream()
                .filter(id -> !foundIds.contains(id))
                .toList();

        if (!missingIds.isEmpty()) {
            throw new ElementNotFoundException("No tables were found for the provided IDs.");
        }
        return tables;
    }

    /**
     * Verifies if the given table DTO has valid restaurant and waiter IDs.
     *
     * @param tableDto The table DTO to verify.
     * @throws ElementNotFoundException if the restaurant or waiters do not exist.
     * @throws ServiceException if an error occurs while fetching data.
     */
    @Transactional(readOnly = true)
    void verifyClientResponses(TableDto tableDto) {
        try {
            List<WaiterDto> waitersDto = waiterClient.getWaitersByIds(tableDto.getWaitersId());
            RestaurantDto restaurantDto = restaurantClient.getRestaurantById(tableDto.getRestaurantId());
            if ((!tableDto.getWaitersId().isEmpty() && waitersDto.isEmpty()) || restaurantDto == null) {
                throw new ElementNotFoundException("Invalid waiter or restaurant IDs");
            }
        } catch (Exception ex) {
            throw new ServiceException("Error fetching waiters data", ex);
        }
    }

}
