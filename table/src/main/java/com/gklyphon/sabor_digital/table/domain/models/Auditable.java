package com.gklyphon.sabor_digital.table.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

/**
 * Base entity class that provides automatic auditing fields for creation and update timestamps.
 * Entities that extend this class will automatically have `createdAt` and `updatedAt` timestamps managed.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
@MappedSuperclass
public class Auditable {

    /** Timestamp indicating when the entity was created. It is not updatable. */
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /** Timestamp indicating when the entity was last updated. */
    private LocalDateTime updatedAt;

    /**
     * Sets the `createdAt` and `updatedAt` timestamps before the entity is persisted.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Updates the `updatedAt` timestamp before the entity is updated.
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
