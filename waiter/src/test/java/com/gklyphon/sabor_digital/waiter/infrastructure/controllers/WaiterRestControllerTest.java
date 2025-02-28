package com.gklyphon.sabor_digital.waiter.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gklyphon.sabor_digital.waiter.TestData;
import com.gklyphon.sabor_digital.waiter.application.dto.WaiterDto;
import com.gklyphon.sabor_digital.waiter.application.services.IWaiterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WaiterRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IWaiterService waiterService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll_shouldReturnWaitersPage() throws Exception {
        when(waiterService.findAll(any(Pageable.class))).thenReturn(TestData.WAITER_PAGE);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/waiters"))
                .andExpect(status().isOk());
    }

    @Test
    void getById_shouldReturnWaiter_whenExists() throws Exception {
        when(waiterService.findById(1L)).thenReturn(TestData.WAITER);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/waiters/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstname").value(TestData.WAITER.getFirstname()));
    }

    @Test
    void save_shouldCreateNewWaiter() throws Exception {
        when(waiterService.save(any(WaiterDto.class))).thenReturn(TestData.WAITER);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/waiters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TestData.WAITER_DTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void update_shouldModifyExistingWaiter() throws Exception {
        when(waiterService.update(anyLong(), any(WaiterDto.class))).thenReturn(TestData.WAITER);
        mockMvc.perform((MockMvcRequestBuilders.put("/api/waiters/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TestData.WAITER_DTO))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void deleteById_shouldRemoveWaiter() throws Exception {
        doNothing().when(waiterService).deleteById(1L);
        mockMvc.perform((MockMvcRequestBuilders.delete("/api/waiters/1")))
                .andExpect(status().isNoContent());
    }

}