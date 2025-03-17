package com.gklyphon.sabor_digital.order.infrastructure.controller;

import com.gklyphon.sabor_digital.order.application.dtos.OrderDto;
import com.gklyphon.sabor_digital.order.application.services.IOrderService;
import com.gklyphon.sabor_digital.order.domain.models.Order;
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
 * REST controller for managing orders.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
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
    @GetMapping("/all-by-restaurant/{id}")
    public ResponseEntity<?> getAllByRestaurant(
            @PathVariable Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(buildPagedModel(orderService.findAllByRestaurantId(id, pageable)));
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id the ID of the order
     * @return a ResponseEntity containing the order
     */
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
    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderDto orderDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(orderDto));
    }

    /**
     * Updates an existing order by its ID.
     *
     * @param id       the ID of the order to update
     * @param orderDto the DTO containing updated order data
     * @return a ResponseEntity containing the updated order
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.update(id, orderDto));
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id the ID of the order to delete
     * @return a ResponseEntity with no content
     */
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
