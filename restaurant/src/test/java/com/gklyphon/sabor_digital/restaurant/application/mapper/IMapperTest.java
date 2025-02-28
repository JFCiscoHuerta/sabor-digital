package com.gklyphon.sabor_digital.restaurant.application.mapper;

import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuDto;
import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuItemDto;
import com.gklyphon.sabor_digital.restaurant.application.dtos.RestaurantDto;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Menu;
import com.gklyphon.sabor_digital.restaurant.domain.entities.MenuItem;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Restaurant;
import com.gklyphon.sabor_digital.restaurant.utils.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IMapperTest {

    @Mock
    IMapper mapper;

    @Test
    void fromRestaurantToRestaurantDTO_shouldMapCorrectly() {
        when(mapper.fromRestaurantToRestaurantDTO(any(Restaurant.class))).thenReturn(TestData.RESTAURANT_DTO);
        RestaurantDto dto = mapper.fromRestaurantToRestaurantDTO(TestData.RESTAURANT);
        assertNotNull(dto);
        assertEquals(TestData.RESTAURANT.getName(), dto.getName());
        assertEquals(TestData.RESTAURANT.getAddress(), dto.getAddress());
        assertEquals(TestData.RESTAURANT.getPhone(), dto.getPhone());
        assertEquals(TestData.RESTAURANT.getWebsite(), dto.getWebsite());
        assertEquals(TestData.RESTAURANT.getLogo(), dto.getLogo());
    }

    @Test
    void fromRestaurantDtoToRestaurant_shouldMapCorrectly() {
        when(mapper.fromRestaurantDtoToRestaurant(any(RestaurantDto.class))).thenReturn(TestData.RESTAURANT);
        Restaurant entity = mapper.fromRestaurantDtoToRestaurant(TestData.RESTAURANT_DTO);
        assertNotNull(entity);
        assertEquals(TestData.RESTAURANT_DTO.getName(), entity.getName());
        assertEquals(TestData.RESTAURANT_DTO.getAddress(), entity.getAddress());
        assertEquals(TestData.RESTAURANT_DTO.getPhone(), entity.getPhone());
        assertEquals(TestData.RESTAURANT_DTO.getWebsite(), entity.getWebsite());
        assertEquals(TestData.RESTAURANT_DTO.getLogo(), entity.getLogo());
    }

    @Test
    void fromMenuDtoToMenu_shouldMapCorrectly() {
        when(mapper.fromMenuDtoToMenu(any(MenuDto.class))).thenReturn(TestData.MENU);
        Menu menu = mapper.fromMenuDtoToMenu(TestData.MENU_DTO);
        assertNotNull(menu);
        assertEquals(TestData.MENU_DTO.getName(), menu.getName());
    }

    @Test
    void fromMenuItemDtoToMenuItem_shouldMapCorrectly() {
        when(mapper.fromMenuItemDtoToMenuItem(any(MenuItemDto.class))).thenReturn(TestData.MENU_ITEM);
        MenuItem menuItem = mapper.fromMenuItemDtoToMenuItem(TestData.MENU_ITEM_DTO);
        assertNotNull(menuItem);
        assertEquals(TestData.MENU_ITEM_DTO.getName(), menuItem.getName());
    }

}