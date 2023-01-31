package com.security.SpringSecurity.Repository;

import com.security.SpringSecurity.Entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing Authority entity from a data store.
 * communicates with the database for actual data access.
 * extends JpaRepository for standard implementation of data access layer
 */
@Repository
public interface AuthorityRepo extends JpaRepository<Authority,Integer> {

    Optional<Authority> findByName (String authority);
}
