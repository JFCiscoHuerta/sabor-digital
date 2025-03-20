package com.gklyphon.sabor_digital.restaurant.infrastructure.controllers;

import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuDto;
import com.gklyphon.sabor_digital.restaurant.application.services.IMenuService;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Menu;
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

/**
 * REST Controller for managing menus.
 * Provides endpoints for CRUD operations.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
@Tag(name = "Menu", description = "Endpoints for managing menus")
@RestController
@RequestMapping("/api/menus")
public class MenuRestController {

    private final IMenuService menuService;
    private final PagedResourcesAssembler<Menu> pagedResourcesAssembler;

    /**
     * Constructs a new {@code MenuRestController} with required dependencies.
     *
     * @param menuService Service handling business logic for menus.
     * @param pagedResourcesAssembler Assembler for paginated HATEOAS responses.
     */
    public MenuRestController(IMenuService menuService, PagedResourcesAssembler<Menu> pagedResourcesAssembler) {
        this.menuService = menuService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    /**
     * Retrieves a paginated list of menus.
     *
     * @param page Page number (default: 0).
     * @param size Number of items per page (default: 10).
     * @return A paginated list of menus wrapped in {@code PagedModel}.
     */
    @Operation(summary = "Get all menus", description = "Retrieves a paginated list of menus")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    @GetMapping
    public ResponseEntity<?> getAll(
            @Parameter(description = "Page number", example = "0") @RequestParam(name = "page", defaultValue = "0") int page,
            @Parameter(description = "Number of items per page", example = "10") @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                handlePageModels(menuService.findAll(pageable)));
    }

    /**
     * Retrieves a specific menu by its ID.
     *
     * @param id The ID of the menu.
     * @return The found menu.
     */
    @Operation(summary = "Get a menu by ID", description = "Retrieves a menu by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu found"),
            @ApiResponse(responseCode = "404", description = "Menu not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@Parameter(description = "Menu ID", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(menuService.findById(id));
    }

    /**
     * Creates a new menu.
     *
     * @param menuDto The DTO containing menu data.
     * @return The created menu.
     */
    @Operation(summary = "Create a new menu", description = "Creates a new menu based on the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Menu created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MenuDto menuDto, BindingResult result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                menuService.save(menuDto));
    }

    /**
     * Updates an existing menu.
     *
     * @param id The ID of the menu to update.
     * @param menuDto The DTO with updated menu data.
     * @return The updated menu.
     */
    @Operation(summary = "Update a menu", description = "Updates an existing menu by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu updated successfully"),
            @ApiResponse(responseCode = "404", description = "Menu not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @Parameter(description = "Menu ID", example = "1") @PathVariable Long id,
            @Valid @RequestBody MenuDto menuDto, BindingResult result) {
        return ResponseEntity.ok(menuService.update(id, menuDto));
    }

    /**
     * Deletes a menu by its ID.
     *
     * @param id The ID of the menu to delete.
     * @return A {@code 204 No Content} response.
     */
    @Operation(summary = "Delete a menu", description = "Deletes a menu by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Menu deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Menu not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@Parameter(description = "Menu ID", example = "1") @PathVariable Long id) {
        menuService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Converts a paginated list of menus into a HATEOAS-compatible {@code PagedModel}.
     *
     * @param page The paginated list of menus.
     * @return A paginated HATEOAS model of menus.
     */
    private PagedModel<EntityModel<Menu>> handlePageModels(Page<Menu> page) {
        return pagedResourcesAssembler.toModel(page);
    }
}
