package com.gklyphon.sabor_digital.restaurant.application.mapper;

import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuDto;
import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuItemDto;
import com.gklyphon.sabor_digital.restaurant.application.dtos.RestaurantDto;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Menu;
import com.gklyphon.sabor_digital.restaurant.domain.entities.MenuItem;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Restaurant;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMapper {

    Restaurant fromRestaurantDtoToRestaurant(RestaurantDto restaurantDTO);
    @Mapping(source = "restaurantId", target = "restaurant.id")
    Menu fromMenuDtoToMenu(MenuDto menuDto);
    @Mapping(source ="menuId", target = "menu.id")
    MenuItem fromMenuItemDtoToMenuItem(MenuItemDto menuItem);

    @InheritInverseConfiguration
    RestaurantDto fromRestaurantToRestaurantDTO(Restaurant restaurant);
    MenuDto fromMenuToMenuDto(Menu menu);
    MenuItemDto fromMenuItemToMenuItemDto(MenuItem menuItem);
}
