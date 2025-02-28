package com.gklyphon.sabor_digital.restaurant.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuDto;
import com.gklyphon.sabor_digital.restaurant.application.services.IMenuService;
import com.gklyphon.sabor_digital.restaurant.application.services.impl.MenuServiceImpl;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Menu;
import com.gklyphon.sabor_digital.restaurant.utils.TestData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MenuRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MenuServiceImpl menuService;

    @Test
    void getById_shouldReturnMenu_whenFound() throws Exception {
        when(menuService.findById(1L)).thenReturn(new Menu());

        mockMvc.perform(get("/api/menus/1"))
                .andExpect(status().isOk());
    }

    @Test
    void create_shouldReturnCreatedMenu() throws Exception {
        when(menuService.save(any(MenuDto.class))).thenReturn(new Menu());

        mockMvc.perform(post("/api/menus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TestData.MENU_DTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void update_shouldReturnUpdatedMenu() throws Exception {
        when(menuService.update(Mockito.eq(1L), any(MenuDto.class))).thenReturn(new Menu());

        mockMvc.perform(put("/api/menus/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TestData.MENU_DTO)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteById_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/menus/1"))
                .andExpect(status().isNoContent());
    }
}