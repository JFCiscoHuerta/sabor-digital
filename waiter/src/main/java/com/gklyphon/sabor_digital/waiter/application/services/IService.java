package com.gklyphon.sabor_digital.waiter.application.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Generic service interface that defines basic CRUD operations.
 *
 * @param <T> The entity type returned by service methods.
 * @param <N> The DTO or entity type used for creating and updating records.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
public interface IService <T, N>{

    /**
     * Retrieves all tables with pagination.
     *
     * @param pageable the pagination and sorting information.
     * @return a paginated list of tables.
     */
    Page<T> findAll(Pageable pageable);

    /**
     * Finds an entity by its unique identifier.
     *
     * @param id The unique identifier of the entity.
     * @return The entity found, or {@code null} if no entity exists with the given ID.
     */
    T findById(Long id);

    /**
     * Saves a new entity in the database.
     *
     * @param n The data transfer object (DTO) or entity containing the necessary information.
     * @return The saved entity.
     */
    T save(N n);

    /**
     * Updates an existing entity with new information.
     *
     * @param id The unique identifier of the entity to be updated.
     * @param n The data transfer object (DTO) or entity containing the updated information.
     * @return The updated entity.
     */
    T update(Long id, N n);

    /**
     * Deletes an entity by its unique identifier.
     *
     * @param id The unique identifier of the entity to be deleted.
     */
    void deleteById(Long id);
}
