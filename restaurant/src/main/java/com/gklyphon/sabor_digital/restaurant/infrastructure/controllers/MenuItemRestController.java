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

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemRestController {

    private final IMenuItemService menuItemService;
    private final PagedResourcesAssembler<MenuItem> pagedResourcesAssembler;

    public MenuItemRestController(IMenuItemService menuItemService, PagedResourcesAssembler<MenuItem> pagedResourcesAssembler) {
        this.menuItemService = menuItemService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(buildPagedModel(menuItemService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MenuItemDto menuItemDto) {
        MenuItem menuItem = menuItemService.save(menuItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MenuItemDto menuItemDto) {
        return ResponseEntity.ok(menuItemService.update(id, menuItemDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        menuItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private PagedModel<EntityModel<MenuItem>> buildPagedModel(Page<MenuItem> menuItems) {
        return pagedResourcesAssembler.toModel(menuItems);
    }

}
