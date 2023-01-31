package com.security.SpringSecurity.Service;

import com.security.SpringSecurity.Entity.Authority;
import com.security.SpringSecurity.Exception.AuthorityException;
import com.security.SpringSecurity.Exception.ResourceNotFoundException;

import java.util.List;

/**
 * Service Interface for performing operations on Authority
 */
public interface AuthorityService {

    Authority createAuthority (Authority authority) throws AuthorityException;

    String deleteAuthorityById (Integer authority_id) throws ResourceNotFoundException, AuthorityException;

    String deleteAuthorityByName (String name) throws ResourceNotFoundException, AuthorityException;

    Authority getAuthorityById (Integer authority_id) throws ResourceNotFoundException;

    Authority getAuthorityByName (String name) throws ResourceNotFoundException;

    List<Authority> getAllAuthority () throws ResourceNotFoundException;

    Authority updateAuthorityById (Integer authority_id, Authority authority) throws ResourceNotFoundException;

    Authority updateAuthorityByName (String name, Authority authority) throws ResourceNotFoundException;
}
