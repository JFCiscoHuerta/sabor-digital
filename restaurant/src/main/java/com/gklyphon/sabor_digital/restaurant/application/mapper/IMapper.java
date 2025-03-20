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

/**
 * Mapper interface for converting between Restaurant entities and Restaurant DTOs.
 *
 * <p>Utilizes MapStruct for automatic implementation generation.</p>
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
@Mapper(componentModel = "spring")
public interface IMapper {
    /**
     * Converts a RestaurantDto to a Restaurant entity.
     *
     * @param restaurantDTO the RestaurantDto to convert
     * @return the corresponding Restaurant entity
     */
    Restaurant fromRestaurantDtoToRestaurant(RestaurantDto restaurantDTO);

    /**
     * Converts a MenuDto to a Menu entity, mapping the restaurantId to the restaurant's id.
     *
     * @param menuDto the MenuDto to convert
     * @return the corresponding Menu entity
     */
    @Mapping(source = "restaurantId", target = "restaurant.id")
    Menu fromMenuDtoToMenu(MenuDto menuDto);

    /**
     * Converts a MenuItemDto to a MenuItem entity, mapping the menuId to the menu's id.
     *
     * @param menuItem the MenuItemDto to convert
     * @return the corresponding MenuItem entity
     */
    @Mapping(source ="menuId", target = "menu.id")
    MenuItem fromMenuItemDtoToMenuItem(MenuItemDto menuItem);
    /**
     * Converts a Restaurant entity to a RestaurantDto, using inverse configuration.
     *
     * @param restaurant the Restaurant entity to convert
     * @return the corresponding RestaurantDto
     */
    @InheritInverseConfiguration
    RestaurantDto fromRestaurantToRestaurantDTO(Restaurant restaurant);

    /**
     * Converts a Menu entity to a MenuDto.
     *
     * @param menu the Menu entity to convert
     * @return the corresponding MenuDto
     */
    MenuDto fromMenuToMenuDto(Menu menu);

    /**
     * Converts a MenuItem entity to a MenuItemDto.
     *
     * @param menuItem the MenuItem entity to convert
     * @return the corresponding MenuItemDto
     */
    MenuItemDto fromMenuItemToMenuItemDto(MenuItem menuItem);
}
