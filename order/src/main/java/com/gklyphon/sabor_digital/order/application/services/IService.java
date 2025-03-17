package com.gklyphon.sabor_digital.order.application.services;

/**
 * Generic service interface providing basic CRUD operations.
 *
 * @param <T> the type of the entity
 * @param <N> the type if the data transfer object (DTO) used for creating or updating entities
 *
 * @author JFCiscoHuerta
 * created on 2025/03/16
 */
public interface IService <T, N>{

    /**
     * Finds an entity by its ID.
     *
     * @param id the ID of the entity
     * @return the entity if found
     */
    T findById(Long id);

    /**
     * Saves a new entity based on the provided DTO.
     *
     * @param n the DTO representing the new entity
     * @return the saved entity
     */
    T save(N n);

    /**
     * Updates an existing entity identified by its ID using the provided DTO.
     *
     * @param id the ID of the entity to update
     * @param n the DTO containing the updated data
     * @return the updated entity
     */
    T update(Long id, N n);

    /**
     * Deletes an existing entity by its ID.
     * @param id the id of the entity to delete
     */
    void deleteById(Long id);
}
