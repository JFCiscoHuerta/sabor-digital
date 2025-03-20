package com.gklyphon.sabor_digital.table.infrastructure.controllers;

import com.gklyphon.sabor_digital.table.application.dtos.TableDto;
import com.gklyphon.sabor_digital.table.application.services.ITableService;
import com.gklyphon.sabor_digital.table.domain.models.Table;
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
 * REST controller for managing tables.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
@Tag(name = "Tables", description = "Operations related to restaurant tables")
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
    @Operation(summary = "Get all tables by restaurant", description = "Retrieves a paginated list of tables for a given restaurant ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tables retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    @GetMapping("/all-by-restaurant/{id}")
    public ResponseEntity<?> getAllByRestaurant(
            @Parameter(description = "Restaurant ID") @PathVariable(name = "id") Long id,
            @Parameter(description = "Page number (default: 0)") @RequestParam(name = "page", defaultValue = "0") int page,
            @Parameter(description = "Page size (default: 10)") @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(buildPagedModel(tableService.findAllByRestaurantId(id, pageable)));
    }

    /**
     * Retrieves a table by its ID.
     *
     * @param id The ID of the table.
     * @return The found table.
     */
    @Operation(summary = "Get table by ID", description = "Retrieves a table based on its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Table retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Table not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @Parameter(description = "Table ID") @PathVariable Long id) {
        return ResponseEntity.ok(tableService.findById(id));
    }

    /**
     * Creates a new table.
     *
     * @param tableDto The DTO containing table data.
     * @return The created table.
     */
    @Operation(summary = "Create a new table", description = "Adds a new table to the system")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Table created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid table data")
    })
    @PostMapping
    public ResponseEntity<?> create(
            @Parameter(description = "Table data") @Valid @RequestBody TableDto tableDto, BindingResult result) {
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
    @Operation(summary = "Update a table", description = "Updates an existing table by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Table updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid table data"),
            @ApiResponse(responseCode = "404", description = "Table not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @Parameter(description = "Table ID") @PathVariable Long id,
            @Parameter(description = "Updated table data") @Valid @RequestBody TableDto tableDto, BindingResult result) {
        return ResponseEntity.ok(tableService.update(id, tableDto));
    }

    /**
     * Deletes a table by its ID.
     *
     * @param id The ID of the table to delete.
     * @return A response with no content.
     */
    @Operation(summary = "Delete a table", description = "Removes a table from the system by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Table deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Table not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @Parameter(description = "Table ID") @PathVariable Long id) {
        tableService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves a list of tables by their IDs.
     *
     * @param ids The list of table IDs.
     * @return A list of tables.
     */
    @Operation(summary = "Get tables by multiple IDs", description = "Retrieves a list of tables given their IDs")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tables retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid table IDs provided")
    })
    @GetMapping("/by-ids")
    public ResponseEntity<?> getByIds(
            @Parameter(description = "List of table IDs") @RequestParam List<Long> ids) {
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
