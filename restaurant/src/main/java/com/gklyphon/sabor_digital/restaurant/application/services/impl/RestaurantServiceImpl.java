package com.gklyphon.sabor_digital.restaurant.application.services.impl;

import com.gklyphon.sabor_digital.restaurant.application.dtos.RestaurantDto;
import com.gklyphon.sabor_digital.restaurant.application.mapper.IMapper;
import com.gklyphon.sabor_digital.restaurant.application.services.IRestaurantService;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Restaurant;
import com.gklyphon.sabor_digital.restaurant.infrastructure.repositories.IRestaurantRepository;
import com.gklyphon.sabor_digital.restaurant.infrastructure.exception.exceptions.ElementNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link IRestaurantService} that provides CRUD operations for restaurants.
 * This service interacts with the {@link IRestaurantRepository} to manage restaurant data.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
@Service
public class RestaurantServiceImpl implements IRestaurantService {

    private final IRestaurantRepository restaurantRepository;
    private final IMapper mapper;

    /**
     * Constructs a new {@code RestaurantServiceImpl}.
     *
     * @param restaurantRepository The repository for managing restaurant persistence.
     * @param mapper The mapper for converting between DTOs and entities.
     */
    public RestaurantServiceImpl(IRestaurantRepository restaurantRepository, IMapper mapper) {
        this.restaurantRepository = restaurantRepository;
        this.mapper = mapper;
    }

    /**
     * Finds a restaurant by its unique identifier.
     *
     * @param id The unique identifier of the restaurant.
     * @return The found {@link Restaurant} entity.
     * @throws ElementNotFoundException If no restaurant is found with the given ID.
     */
    @Override
    @Transactional(readOnly = true)
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Restaurant not found."));
    }

    /**
     * Saves a new restaurant entity based on the provided DTO.
     *
     * @param restaurantDto The DTO containing restaurant details.
     * @return The saved {@link Restaurant} entity.
     * @throws ServiceException If an error occurs during the saving process.
     */
    @Override
    @Transactional
    public Restaurant save(RestaurantDto restaurantDto) {
        try {
            return restaurantRepository.save(
                    mapper.fromRestaurantDtoToRestaurant(restaurantDto));
        } catch (Exception ex) {
            throw new ServiceException("An error occurred while saving the restaurant", ex);
        }
    }


    /**
     * Updates an existing restaurant entity with the provided DTO.
     *
     * @param id The unique identifier of the restaurant to update.
     * @param restaurantDto The DTO containing updated restaurant details.
     * @return The updated {@link Restaurant} entity.
     * @throws ElementNotFoundException If no restaurant is found with the given ID.
     * @throws ServiceException If an error occurs during the update process.
     */
    @Override
    @Transactional
    public Restaurant update(Long id, RestaurantDto restaurantDto) {
        Restaurant originalRestaurant = findById(id);
        try {
            BeanUtils.copyProperties(restaurantDto, originalRestaurant, "id");
            return restaurantRepository.save(originalRestaurant);
        } catch (Exception ex) {
            throw new ServiceException("An error occurred while updating the restaurant", ex);
        }
    }

    /**
     * Deletes a restaurant entity by its unique identifier.
     *
     * @param id The unique identifier of the restaurant to delete.
     * @throws ElementNotFoundException If no restaurant is found with the given ID.
     * @throws ServiceException If an error occurs during the deletion process.
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id);
        try {
            restaurantRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceException("An error occurred while deleting the restaurant", ex);
        }
    }
}
