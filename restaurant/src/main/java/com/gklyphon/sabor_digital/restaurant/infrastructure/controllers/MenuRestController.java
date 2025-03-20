package com.gklyphon.sabor_digital.restaurant.infrastructure.controllers;

import com.gklyphon.sabor_digital.restaurant.application.dtos.MenuDto;
import com.gklyphon.sabor_digital.restaurant.application.services.IMenuService;
import com.gklyphon.sabor_digital.restaurant.domain.entities.Menu;
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
 * REST Controller for managing menus.
 * Provides endpoints for CRUD operations.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
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
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
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
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.findById(id));
    }

    /**
     * Creates a new menu.
     *
     * @param menuDto The DTO containing menu data.
     * @return The created menu.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MenuDto menuDto) {
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
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MenuDto menuDto) {
        return ResponseEntity.ok(menuService.update(id, menuDto));
    }

    /**
     * Deletes a menu by its ID.
     *
     * @param id The ID of the menu to delete.
     * @return A {@code 204 No Content} response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
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
