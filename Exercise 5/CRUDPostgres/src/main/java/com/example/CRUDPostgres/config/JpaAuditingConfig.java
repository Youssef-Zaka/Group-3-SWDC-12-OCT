package com.example.CRUDPostgres.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Configuration class that enables JPA Auditing for the application.
 * JPA Auditing is used to automatically fill in auditing fields, such as
 * `createdAt`, `createdBy`, `updatedAt`, and `updatedBy`, in the entities.
 * <p>
 * The `auditorAwareRef` attribute specifies the bean that provides
 * the current auditor (user).
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")  // Reference the AuditorAware bean
public class JpaAuditingConfig {
    // Configuration for JPA Auditing
}
