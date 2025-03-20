package com.gklyphon.sabor_digital.waiter.infrastructure.controllers;

import com.gklyphon.sabor_digital.waiter.application.dto.WaiterDto;
import com.gklyphon.sabor_digital.waiter.application.services.IWaiterService;
import com.gklyphon.sabor_digital.waiter.domain.models.Waiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing waiters.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
@Tag(name = "Waiters", description = "Operations related to waiters")
@RestController
@RequestMapping("/api/waiters")
public class WaiterRestController {

    private final IWaiterService waiterService;
    private final PagedResourcesAssembler<Waiter> pagedResourcesAssembler;

    public WaiterRestController(IWaiterService waiterService, PagedResourcesAssembler<Waiter> pagedResourcesAssembler) {
        this.waiterService = waiterService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    /**
     * Retrieves all waiters with pagination.
     *
     * @param page The page number (default: 0).
     * @param size The number of items per page (default: 10).
     * @return A paginated list of waiters.
     */
    @Operation(summary = "Get all waiters", description = "Retrieves all waiters with pagination.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved waiters"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content),
    })
    @GetMapping
    public ResponseEntity<?> getAll(
            @Parameter(description = "Page number", example = "0") @RequestParam(name = "page", defaultValue = "0") int page,
            @Parameter(description = "Page size", example = "10") @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                buildPagedModel(waiterService.findAll(pageable)));
    }
    /**
     * Retrieves all waiters by restaurant ID with pagination.
     *
     * @param id   The restaurant ID.
     * @param page The page number (default: 0).
     * @param size The number of items per page (default: 10).
     * @return A paginated list of waiters.
     */
    @Operation(summary = "Get waiters by restaurant ID", description = "Retrieves all waiters assigned to a restaurant.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved waiters"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = @Content)
    })
    @GetMapping("/by-restaurant/{id}")
    public ResponseEntity<?> getAllByRestaurantId(
            @Parameter(description = "Restaurant ID", example = "0") @PathVariable(name = "id") Long id,
            @Parameter(description = "Page number", example = "0") @RequestParam(name = "page", defaultValue = "0") int page,
            @Parameter(description = "Page size", example = "10") @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                buildPagedModel(waiterService.findAllByRestaurantId(id, pageable)));
    }

    /**
     * Retrieves a waiter by ID.
     *
     * @param id The ID of the waiter.
     * @return The found waiter.
     */
    @Operation(summary = "Get waiter by ID", description = "Retrieves a waiter by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Waiter found"),
            @ApiResponse(responseCode = "404", description = "Waiter not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @Parameter(description = "Waiter ID", example = "1") @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(
                waiterService.findById(id));
    }

    /**
     * Creates a new waiter.
     *
     * @param waiterDto The DTO containing waiter data.
     * @return The created waiter.
     */
    @Operation(summary = "Create a new waiter", description = "Saves a new waiter in the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Waiter successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> save(
            @Parameter(description = "WaiterDTO")  @Valid @RequestBody WaiterDto waiterDto, BindingResult result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                waiterService.save(waiterDto));
    }

    /**
     * Updates an existing waiter.
     *
     * @param id        The ID of the waiter to update.
     * @param waiterDto The DTO containing updated waiter data.
     * @return The updated waiter.
     */
    @Operation(summary = "Update a waiter", description = "Updates an existing waiter by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Waiter successfully updated"),
            @ApiResponse(responseCode = "404", description = "Waiter not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @Parameter(description = "Waiter ID", example = "1") @PathVariable Long id,
            @Parameter(description = "Updated waiter data") @Valid @RequestBody WaiterDto waiterDto, BindingResult result) {
        return ResponseEntity.ok(waiterService.update(id, waiterDto));
    }

    /**
     * Deletes a waiter by ID.
     *
     * @param id The ID of the waiter to delete.
     * @return A response with no content.
     */
    @Operation(summary = "Delete a waiter", description = "Deletes a waiter by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Waiter successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Waiter not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(
            @Parameter(description = "Waiter ID", example = "0") @PathVariable(name = "ids") Long id) {
        waiterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves a list of waiters by their IDs.
     *
     * @param ids The list of waiter IDs.
     * @return A list of waiters.
     */
    @Operation(summary = "Get waiters by multiple IDs", description = "Retrieves a list of waiters by their IDs.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved waiters"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content)
    })
    @GetMapping("/by-ids")
    public ResponseEntity<?> getWaitersByIds(
            @Parameter(description = "List of waiter IDs", example = "[1,2,3]") @RequestParam List<Long> ids) {
        return ResponseEntity.ok(waiterService.findByIdIn(ids));
    }

    /**
     * Builds a paginated HATEOAS response model from a page of waiters.
     *
     * @param page The page of waiters.
     * @return A paginated model with HATEOAS links.
     */
    private PagedModel<EntityModel<Waiter>> buildPagedModel(Page<Waiter> page) {
        return pagedResourcesAssembler.toModel(page);
    }
}
