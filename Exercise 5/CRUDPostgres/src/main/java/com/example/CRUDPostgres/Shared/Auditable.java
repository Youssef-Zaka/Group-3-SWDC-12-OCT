package com.example.CRUDPostgres.Shared;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Abstract class that provides auditing fields for entities.
 * Includes fields for tracking the creation and modification dates,
 * as well as the users who performed these actions.
 * <p>
 * This class uses Spring Data JPA auditing feature to automatically
 * populate the auditing fields when an entity is created or updated.
 */
@Setter
@Getter
@MappedSuperclass // Indicates that this class is a base class for entity inheritance
@EntityListeners(AuditingEntityListener.class)  // Enables JPA auditing for this class
public abstract class Auditable {

    /**
     * The date and time when the entity was created.
     * Automatically set when the entity is first persisted.
     */
    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * The date and time when the entity was last updated.
     * Automatically set when the entity is updated.
     */
    @LastModifiedDate
    private LocalDateTime updatedAt;

    /**
     * The username of the user who created the entity.
     * Automatically set when the entity is first persisted.
     */
    @CreatedBy
    private String createdBy;

    /**
     * The username of the user who last modified the entity.
     * Automatically set when the entity is updated.
     */
    @LastModifiedBy
    private String updatedBy;
}
