package com.gklyphon.sabor_digital.order.infrastructure.controller;

import com.gklyphon.sabor_digital.order.application.dtos.OrderDto;
import com.gklyphon.sabor_digital.order.application.services.IOrderService;
import com.gklyphon.sabor_digital.order.domain.models.Order;
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
 * REST controller for managing orders.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
@Tag(name = "Order", description = "Endpoints for managing orders")
@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    private final IOrderService orderService;
    private final PagedResourcesAssembler<Order> pagedResourcesAssembler;

    /**
     * Constructs an OrderRestController.
     *
     * @param orderService             the service to handle order operations
     * @param pagedResourcesAssembler  the assembler for paged HATEOAS resources
     */
    public OrderRestController(IOrderService orderService, PagedResourcesAssembler<Order> pagedResourcesAssembler) {
        this.orderService = orderService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    /**
     * Retrieves all orders for a specific restaurant with pagination.
     *
     * @param id    the restaurant ID
     * @param page  the page number (default is 0)
     * @param size  the page size (default is 10)
     * @return a ResponseEntity containing the paged orders
     */
    @Operation(summary = "Retrieves all orders for a specific restaurant",
            description = "Retrieves a paginated list of orders for a given restaurant ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orders retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = @Content)
    })
    @GetMapping("/all-by-restaurant/{id}")
    public ResponseEntity<?> getAllByRestaurant(
            @Parameter(description = "Restaurant ID") @PathVariable(name = "id") Long id,
            @Parameter(description = "Page number", example = "0") @RequestParam(name = "page", defaultValue = "0") int page,
            @Parameter(description = "Page size", example = "10") @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(buildPagedModel(orderService.findAllByRestaurantId(id, pageable)));
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id the ID of the order
     * @return a ResponseEntity containing the order
     */
    @Operation(summary = "Retrieve an order by ID",
            description = "Fetches the details of an order using its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "order not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    /**
     * Creates a new order.
     *
     * @param orderDto the DTO containing order data
     * @return a ResponseEntity containing the created order
     */
    @Operation(summary = "Create a new order",
            description = "Creates a new order with the provided details.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody OrderDto orderDto, BindingResult result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(orderDto));
    }

    /**
     * Updates an existing order by its ID.
     *
     * @param id       the ID of the order to update
     * @param orderDto the DTO containing updated order data
     * @return a ResponseEntity containing the updated order
     */
    @Operation(summary = "Update and existing order",
            description = "Updates and order by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid
            @RequestBody OrderDto orderDto,
            BindingResult result) {
        return ResponseEntity.ok(orderService.update(id, orderDto));
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id the ID of the order to delete
     * @return a ResponseEntity with no content
     */
    @Operation(summary = "Delete an order",
            description = "Deletes an order by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Order deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds a paged model for the given page of orders.
     *
     * @param orders the page of orders
     * @return a PagedModel containing EntityModels of orders
     */
    private PagedModel<EntityModel<Order>> buildPagedModel(Page<Order> orders) {
        return pagedResourcesAssembler.toModel(orders);
    }

}
