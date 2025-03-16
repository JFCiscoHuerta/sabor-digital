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

@Service
public class TableServiceImpl implements ITableService {

    private final ITableRepository tableRepository;
    private final IMapper iMapper;
    private final IWaiterClient waiterClient;
    private final IRestaurantClient restaurantClient;

    public TableServiceImpl(ITableRepository tableRepository, IMapper iMapper, IWaiterClient waiterClient, IRestaurantClient restaurantClient) {
        this.tableRepository = tableRepository;
        this.iMapper = iMapper;
        this.waiterClient = waiterClient;
        this.restaurantClient = restaurantClient;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Table> findAllByRestaurantId(Long restaurantId, Pageable pageable) {
        return tableRepository.findAllByRestaurantId(restaurantId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Table findById(Long id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Table not found."));
    }

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
