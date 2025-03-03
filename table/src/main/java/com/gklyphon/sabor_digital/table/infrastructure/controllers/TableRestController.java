package com.gklyphon.sabor_digital.table.infrastructure.controllers;

import com.gklyphon.sabor_digital.table.application.dtos.TableDto;
import com.gklyphon.sabor_digital.table.application.services.ITableService;
import com.gklyphon.sabor_digital.table.domain.models.Table;
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
@RequestMapping("/api/tables")
public class TableRestController {

    private final ITableService tableService;
    private final PagedResourcesAssembler<Table> pagedResourcesAssembler;

    public TableRestController(ITableService tableService, PagedResourcesAssembler<Table> pagedResourcesAssembler) {
        this.tableService = tableService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping("/all-by-restaurant/{id}")
    public ResponseEntity<?> getAllByRestaurant(
            @PathVariable Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(buildPagedModel(tableService.findAllByRestaurantId(id, pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tableService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TableDto tableDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                tableService.save(tableDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TableDto tableDto) {
        return ResponseEntity.ok(tableService.update(id, tableDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tableService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public PagedModel<EntityModel<Table>> buildPagedModel(Page<Table> page) {
        return pagedResourcesAssembler.toModel(page);
    }

}
