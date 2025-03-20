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

import java.util.List;

/**
 * REST controller for managing tables.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
@RestController
@RequestMapping("/api/tables")
public class TableRestController {

    private final ITableService tableService;
    private final PagedResourcesAssembler<Table> pagedResourcesAssembler;

    /**
     * Constructs a new {@code TableRestController} with required dependencies.
     *
     * @param tableService Service handling table-related operations.
     * @param pagedResourcesAssembler Assembler for paginated responses.
     */
    public TableRestController(ITableService tableService, PagedResourcesAssembler<Table> pagedResourcesAssembler) {
        this.tableService = tableService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    /**
     * Retrieves a paginated list of tables for a specific restaurant.
     *
     * @param id The ID of the restaurant.
     * @param page The page number (default: 0).
     * @param size The number of items per page (default: 10).
     * @return A paginated response containing the tables.
     */
    @GetMapping("/all-by-restaurant/{id}")
    public ResponseEntity<?> getAllByRestaurant(
            @PathVariable Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(buildPagedModel(tableService.findAllByRestaurantId(id, pageable)));
    }

    /**
     * Retrieves a table by its ID.
     *
     * @param id The ID of the table.
     * @return The found table.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tableService.findById(id));
    }

    /**
     * Creates a new table.
     *
     * @param tableDto The DTO containing table data.
     * @return The created table.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TableDto tableDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                tableService.save(tableDto));
    }

    /**
     * Updates an existing table.
     *
     * @param id The ID of the table to update.
     * @param tableDto The DTO containing updated table data.
     * @return The updated table.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TableDto tableDto) {
        return ResponseEntity.ok(tableService.update(id, tableDto));
    }

    /**
     * Deletes a table by its ID.
     *
     * @param id The ID of the table to delete.
     * @return A response with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tableService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves a list of tables by their IDs.
     *
     * @param ids The list of table IDs.
     * @return A list of tables.
     */
    @GetMapping("/by-ids")
    public ResponseEntity<?> getByIds(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(tableService.findByIdIn(ids));
    }

    /**
     * Builds a paginated HATEOAS response model from a page of tables.
     *
     * @param page The page of tables.
     * @return A paginated model with HATEOAS links.
     */
    public PagedModel<EntityModel<Table>> buildPagedModel(Page<Table> page) {
        return pagedResourcesAssembler.toModel(page);
    }

}
