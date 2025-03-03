package com.gklyphon.sabor_digital.table.application.services.impl;

import com.gklyphon.sabor_digital.table.application.dtos.TableDto;
import com.gklyphon.sabor_digital.table.application.mapper.IMapper;
import com.gklyphon.sabor_digital.table.application.services.ITableService;
import com.gklyphon.sabor_digital.table.domain.models.Table;
import com.gklyphon.sabor_digital.table.infrastructure.exception.exceptions.ElementNotFoundException;
import com.gklyphon.sabor_digital.table.infrastructure.repositories.ITableRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TableServiceImpl implements ITableService {

    private final ITableRepository tableRepository;
    private final IMapper iMapper;

    public TableServiceImpl(ITableRepository tableRepository, IMapper iMapper) {
        this.tableRepository = tableRepository;
        this.iMapper = iMapper;
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
        try {
            BeanUtils.copyProperties(tableDto, originalTable, "id");
            return tableRepository.save(originalTable);
        } catch (Exception ex) {
            throw new ServiceException("An error occurred while updating the table", ex);
        }
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        try {
            tableRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceException("An error occurred while deleting the table", ex);
        }
    }
}
