package com.security.SpringSecurity.Repository;

import com.security.SpringSecurity.Entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing Privilege entity from a data store.
 * communicates with the database for actual data access.
 * extends JpaRepository for standard implementation of data access layer
 */
@Repository
public interface PrivilegeRepo extends JpaRepository<Privilege, Integer> {
}
