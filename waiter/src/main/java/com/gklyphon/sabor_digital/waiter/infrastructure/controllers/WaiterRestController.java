package com.gklyphon.sabor_digital.waiter.infrastructure.controllers;

import com.gklyphon.sabor_digital.waiter.application.dto.WaiterDto;
import com.gklyphon.sabor_digital.waiter.application.services.IWaiterService;
import com.gklyphon.sabor_digital.waiter.domain.models.Waiter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waiters")
public class WaiterRestController {

    private final IWaiterService waiterService;
    private final PagedResourcesAssembler<Waiter> pagedResourcesAssembler;

    public WaiterRestController(IWaiterService waiterService, PagedResourcesAssembler<Waiter> pagedResourcesAssembler) {
        this.waiterService = waiterService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                buildPagedModel(waiterService.findAll(pageable)));
    }

    @GetMapping("/by-restaurant/{id}")
    public ResponseEntity<?> getAllByRestaurantId(
            @PathVariable Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                buildPagedModel(waiterService.findAllByRestaurantId(id, pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                waiterService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody WaiterDto waiterDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                waiterService.save(waiterDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody WaiterDto waiterDto) {
        return ResponseEntity.ok(waiterService.update(id, waiterDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(
            @PathVariable Long id) {
        waiterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-ids")
    public ResponseEntity<?> getWaitersByIds(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(waiterService.findByIdIn(ids));
    }

    private PagedModel<EntityModel<Waiter>> buildPagedModel(Page<Waiter> page) {
        return pagedResourcesAssembler.toModel(page);
    }
}
