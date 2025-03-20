package com.gklyphon.sabor_digital.restaurant.infrastructure.controllers;

import com.gklyphon.sabor_digital.restaurant.application.dtos.RestaurantDto;
import com.gklyphon.sabor_digital.restaurant.application.services.IRestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Restaurant", description = "Endpoints for managing restaurants")
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
    @Operation(summary = "Get a restaurant by ID", description = "Retrieves a restaurant by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant found"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @Parameter(description = "Restaurant ID", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.findById(id));
    }

    /**
     * Creates a new restaurant.
     *
     * @param restaurantDto The DTO containing restaurant data.
     * @return The created restaurant.
     */
    @Operation(summary = "Create a new restaurant", description = "Creates a new restaurant based on the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
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
    @Operation(summary = "Update a restaurant", description = "Updates an existing restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant updated successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @Parameter(description = "Restaurant ID", example = "1") @PathVariable(name = "id") Long id,
            @RequestBody RestaurantDto restaurantDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                restaurantService.update(id, restaurantDto));
    }

    /**
     * Deletes a restaurant by its ID.
     *
     * @param id The ID of the restaurant to delete.
     * @return A {@code 204 No Content} response.
     */
    @Operation(summary = "Delete a restaurant", description = "Deletes a restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Restaurant deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(
            @Parameter(description = "Restaurant ID", example = "1") @PathVariable(name = "id") Long id) {
        restaurantService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
