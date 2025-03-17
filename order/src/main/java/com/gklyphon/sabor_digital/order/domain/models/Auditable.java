package com.gklyphon.sabor_digital.order.domain.models;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

/**
 * Abstract class that provides auditing fields for entity classes.
 * Automatically sets the creation and update timestamps.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
@MappedSuperclass
public class Auditable {

    /**
     * The timestamp when the entity was created.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when the entity was last updated.
     */
    private LocalDateTime updatedAt;

    /**
     * Sets the creation and update timestamps before the entity is persisted.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Updates the update timestamp before the entity is updated.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
