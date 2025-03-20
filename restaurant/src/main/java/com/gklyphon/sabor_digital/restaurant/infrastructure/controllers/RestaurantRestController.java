package com.gklyphon.sabor_digital.restaurant.infrastructure.controllers;

import com.gklyphon.sabor_digital.restaurant.application.dtos.RestaurantDto;
import com.gklyphon.sabor_digital.restaurant.application.services.IRestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing restaurants.
 * Provides endpoints for CRUD operations.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantRestController {

    private final IRestaurantService restaurantService;

    /**
     * Constructs a new {@code RestaurantRestController} with the required service dependency.
     *
     * @param restaurantService Service handling business logic for restaurants.
     */
    public RestaurantRestController(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /**
     * Retrieves a restaurant by its ID.
     *
     * @param id The ID of the restaurant to retrieve.
     * @return The found restaurant.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.findById(id));
    }

    /**
     * Creates a new restaurant.
     *
     * @param restaurantDto The DTO containing restaurant data.
     * @return The created restaurant.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody RestaurantDto restaurantDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                restaurantService.save(restaurantDto));
    }

    /**
     * Updates an existing restaurant.
     *
     * @param id The ID of the restaurant to update.
     * @param restaurantDto The DTO with updated restaurant data.
     * @return The updated restaurant.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RestaurantDto restaurantDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                restaurantService.update(id, restaurantDto));
    }

    /**
     * Deletes a restaurant by its ID.
     *
     * @param id The ID of the restaurant to delete.
     * @return A {@code 204 No Content} response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        restaurantService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
