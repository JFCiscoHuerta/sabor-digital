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

@Service
public class WaiterServiceImpl implements IWaiterService {

    private final IWaiterRepository waiterRepository;
    private final IMapper mapper;
    private final IRestaurantClient restaurantClient;
    private final ITableClient tableClient;

    public WaiterServiceImpl(IWaiterRepository waiterRepository, IMapper mapper, IRestaurantClient restaurantClient, ITableClient tableClient) {
        this.waiterRepository = waiterRepository;
        this.mapper = mapper;
        this.restaurantClient = restaurantClient;
        this.tableClient = tableClient;
    }

    @Override
    @Transactional(readOnly = true)
    public Waiter findById(Long id) {
        return waiterRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("W"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Waiter> findAllByRestaurantId(Long restaurantId, Pageable pageable) {
        return waiterRepository.findAllByRestaurantId(restaurantId, pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Waiter> findAll(Pageable pageable) {
        return waiterRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Waiter save(WaiterDto waiterDto) {
        verifyClientResponses(waiterDto);
        return waiterRepository.save(mapper.fromWaiterDtoToWaiter(waiterDto));
    }

    @Override
    @Transactional
    public Waiter update(Long id, WaiterDto waiterDto) {
        Waiter originalWaiter = findById(id);
        verifyClientResponses(waiterDto);
        try {
            BeanUtils.copyProperties(waiterDto, originalWaiter, "id");
            return waiterRepository.save(originalWaiter);
        } catch (Exception ex) {
            throw new ServiceException("",ex);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id);
        try {
            waiterRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceException("", ex);
        }
    }

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
