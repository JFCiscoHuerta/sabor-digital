package com.gklyphon.sabor_digital.restaurant.infrastructure.controllers;

import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuItemDto;
import com.gklyphon.sabor_digital.restaurant.application.services.IMenuItemService;
import com.gklyphon.sabor_digital.restaurant.domain.entities.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * REST Controller for managing menu items.
 * Provides CRUD operations.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
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
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(buildPagedModel(menuItemService.findAll(pageable)));
    }

    /**
     * Retrieves a specific menu item by its ID.
     *
     * @param id The ID of the menu item.
     * @return The found menu item.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.findById(id));
    }

    /**
     * Creates a new menu item.
     *
     * @param menuItemDto The DTO containing menu item data.
     * @return The created menu item.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MenuItemDto menuItemDto) {
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
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MenuItemDto menuItemDto) {
        return ResponseEntity.ok(menuItemService.update(id, menuItemDto));
    }

    /**
     * Deletes a menu item by its ID.
     *
     * @param id The ID of the menu item to delete.
     * @return A {@code 204 No Content} response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        menuItemService.deleteById(id);
        return ResponseEntity.noContent().build();
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
