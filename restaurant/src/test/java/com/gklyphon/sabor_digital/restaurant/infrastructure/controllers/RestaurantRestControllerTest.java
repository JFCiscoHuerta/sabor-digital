package com.gklyphon.sabor_digital.restaurant.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gklyphon.sabor_digital.restaurant.application.dtos.RestaurantDto;
import com.gklyphon.sabor_digital.restaurant.application.services.impl.RestaurantServiceImpl;
import com.gklyphon.sabor_digital.restaurant.utils.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class RestaurantRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RestaurantServiceImpl restaurantService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getById_shouldReturnRestaurant_whenFound() throws Exception {
        when(restaurantService.findById(anyLong())).thenReturn(TestData.RESTAURANT);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/restaurants/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void create_shouldReturnCreatedRestaurant() throws Exception {
        when(restaurantService.save(any(RestaurantDto.class))).thenReturn(TestData.RESTAURANT);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TestData.RESTAURANT_DTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void update_shouldReturnUpdatedRestaurant() throws Exception {
        when(restaurantService.update(anyLong(), any(RestaurantDto.class))).thenReturn(TestData.RESTAURANT);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/restaurants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TestData.RESTAURANT_DTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void deleteById_shouldReturnNoContent() throws Exception {
        doNothing().when(restaurantService).deleteById(anyLong());
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/restaurants/1"))
                .andExpect(status().isNoContent());
    }

}