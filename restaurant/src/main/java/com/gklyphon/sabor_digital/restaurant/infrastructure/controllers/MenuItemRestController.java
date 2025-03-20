package com.gklyphon.sabor_digital.restaurant.infrastructure.controllers;

import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuItemDto;
import com.gklyphon.sabor_digital.restaurant.application.services.IMenuItemService;
import com.gklyphon.sabor_digital.restaurant.domain.entities.MenuItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
 * REST Controller for managing menu items.
 * Provides CRUD operations.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
@Tag(name = "Menu Items", description = "Endpoints for managing menu items")
@RestController
@RequestMapping("/api/menu-items")
public class MenuItemRestController {

    private final IMenuItemService menuItemService;
    private final PagedResourcesAssembler<MenuItem> pagedResourcesAssembler;

    /**
     * Constructs a new {@code MenuItemRestController} with required dependencies.
     *
     * @param menuItemService Service handling business logic for menu items.
     * @param pagedResourcesAssembler Assembler for paginated HATEOAS responses.
     */
    public MenuItemRestController(IMenuItemService menuItemService, PagedResourcesAssembler<MenuItem> pagedResourcesAssembler) {
        this.menuItemService = menuItemService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    /**
     * Retrieves a paginated list of menu items.
     *
     * @param page Page number (default: 0).
     * @param size Number of items per page (default: 10).
     * @return A paginated list of menu items wrapped in {@code PagedModel}.
     */
    @Operation(summary = "Get All menu items", description = "Retrieves a paginated list of menu items.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of menu items retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Invalid request parameters")
    })
    @GetMapping
    public ResponseEntity<?> getAll(
            @Parameter(description = "Page number") @RequestParam(value = "page", defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(buildPagedModel(menuItemService.findAll(pageable)));
    }

    /**
     * Retrieves a specific menu item by its ID.
     *
     * @param id The ID of the menu item.
     * @return The found menu item.
     */
    @Operation(summary = "Get a menu item by ID", description = "Retrieves a single menu item based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu item found"),
            @ApiResponse(responseCode = "404", description = "Menu item not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@Parameter(description = "Menu Item ID") @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(menuItemService.findById(id));
    }

    /**
     * Creates a new menu item.
     *
     * @param menuItemDto The DTO containing menu item data.
     * @return The created menu item.
     */
    @Operation(summary = "Create a new menu item", description = "Adds a new menu item to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Menu item created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MenuItemDto menuItemDto, BindingResult result) {
        MenuItem menuItem = menuItemService.save(menuItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItem);
    }

    /**
     * Updates an existing menu item.
     *
     * @param id The ID of the menu item to update.
     * @param menuItemDto The DTO with updated menu item data.
     * @return The updated menu item.
     */
    @Operation(summary = "Update a menu item", description = "Updates an existing menu item with new information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu item updated successfully"),
            @ApiResponse(responseCode = "404", description = "Menu item not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody MenuItemDto menuItemDto, BindingResult result) {
        return ResponseEntity.ok(menuItemService.update(id, menuItemDto));
    }

    /**
     * Deletes a menu item by its ID.
     *
     * @param id The ID of the menu item to delete.
     * @return A {@code 204 No Content} response.
     */
    @Operation(summary = "Delete a menu item", description = "Removes a menu item from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Menu item deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Menu item not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        menuItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves a list of menu items by their unique identifiers.
     *
     * @param ids List of menu item IDs to retrieve.
     * @return A {@link ResponseEntity} containing a list of {@link MenuItem} objects.
     *
     * @author Your Name
     * @date 2025-03-20
     */
    @Operation(
            summary = "Get menu items by IDs",
            description = "Retrieves a list of menu items based on the provided IDs."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of menu items retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
            @ApiResponse(responseCode = "404", description = "Some menu items were not found")
    })
    @GetMapping("/by-ids")
    public ResponseEntity<?> getByIds(@RequestParam(name = "ids") List<Long> ids) {
        return ResponseEntity.ok(menuItemService.findByIdIn(ids));
    }

    /**
     * Converts a paginated list of menu items into a HATEOAS-compatible {@code PagedModel}.
     *
     * @param menuItems The paginated list of menu items.
     * @return A paginated HATEOAS model of menu items.
     */
    private PagedModel<EntityModel<MenuItem>> buildPagedModel(Page<MenuItem> menuItems) {
        return pagedResourcesAssembler.toModel(menuItems);
    }

}
