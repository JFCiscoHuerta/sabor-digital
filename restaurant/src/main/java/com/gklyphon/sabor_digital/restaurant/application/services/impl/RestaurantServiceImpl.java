package com.gklyphon.sabor_digital.restaurant.application.services.impl;

import com.gklyphon.sabor_digital.restaurant.application.dtos.RestaurantDto;
import com.gklyphon.sabor_digital.restaurant.application.mapper.IMapper;
import com.gklyphon.sabor_digital.restaurant.application.services.IRestaurantService;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Restaurant;
import com.gklyphon.sabor_digital.restaurant.domain.repositories.IRestaurantRepository;
import com.gklyphon.sabor_digital.restaurant.infrastructure.exception.exceptions.ElementNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

    private final IRestaurantRepository restaurantRepository;
    private final IMapper mapper;

    public RestaurantServiceImpl(IRestaurantRepository restaurantRepository, IMapper mapper) {
        this.restaurantRepository = restaurantRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Restaurant with id not found."));
    }

    @Override
    @Transactional
    public Restaurant save(RestaurantDto restaurantDto) {
        try {
            return restaurantRepository.save(
                    mapper.fromRestaurantDtoToRestaurant(restaurantDto));
        } catch (Exception ex) {
            throw new ServiceException("", ex);
        }
    }

    @Override
    @Transactional
    public Restaurant update(Long id, RestaurantDto restaurantDto) {
        Restaurant originalRestaurant = findById(id);
        try {
            BeanUtils.copyProperties(restaurantDto, originalRestaurant, "id");
            return restaurantRepository.save(originalRestaurant);
        } catch (Exception ex) {
            throw new ServiceException("", ex);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id);
        try {
            restaurantRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceException("", ex);
        }
    }
}
