package com.gklyphon.sabor_digital.restaurant.application.mapper;

import com.gklyphon.sabor_digital.restaurant.application.dtos.RestaurantDto;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Restaurant;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMapper {

    RestaurantDto fromRestaurantToRestaurantDTO(Restaurant restaurant);

    @InheritInverseConfiguration
    Restaurant fromRestaurantDtoToRestaurant(RestaurantDto restaurantDTO);

}
