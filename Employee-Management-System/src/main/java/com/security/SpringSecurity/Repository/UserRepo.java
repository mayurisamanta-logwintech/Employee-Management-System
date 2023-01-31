package com.security.SpringSecurity.Repository;

import com.security.SpringSecurity.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing User entity from a data store.
 * communicates with the database for actual data access.
 * extends JpaRepository for standard implementation of data access layer
 */
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
}
