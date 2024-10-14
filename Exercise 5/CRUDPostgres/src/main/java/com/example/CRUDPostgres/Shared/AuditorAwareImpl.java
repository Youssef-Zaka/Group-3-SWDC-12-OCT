package com.example.CRUDPostgres.Shared;

import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of the AuditorAware interface to provide the current
 * auditor (user) for JPA auditing purposes.
 * <p>
 * This class retrieves the currently authenticated user from the
 * SecurityContext and returns the username to populate the `createdBy`
 * and `updatedBy` fields in entities.
 */
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    /**
     * Retrieves the current auditor (user) from the security context.
     *
     * @return The username of the authenticated user, or an empty
     *         Optional if no user is authenticated.
     */
    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // If no authentication or the user is not authenticated, return an empty Optional
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        // Return the username of the authenticated user
        return Optional.ofNullable(authentication.getName());
    }

    /**
     * Defines a bean for AuditorAware that will be used by Spring Data JPA
     * to get the current auditor for populating auditing fields.
     *
     * @return AuditorAware bean providing the current auditor.
     */
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
