package com.security.SpringSecurity.Service;

import com.security.SpringSecurity.Entity.User;
import com.security.SpringSecurity.Exception.AuthorityException;
import com.security.SpringSecurity.Exception.ResourceNotFoundException;
import com.security.SpringSecurity.Repository.AuthorityRepo;
import com.security.SpringSecurity.Entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to Authority
 * Provides the implementation of AuthorityService Interface
 * communicates with the repository layer (AuthorityRepo) for database access.
 */
@Service
public class AuthorityServiceImpl implements AuthorityService{

    @Autowired
    private AuthorityRepo aRepo;

    // Save new Authority in database by taking Authority object
    @Override
    public Authority createAuthority(Authority authority) throws AuthorityException {
        String authorityName = authority.getName().toUpperCase();

        Optional<Authority> foundAuthority = aRepo.findByName(authorityName);

        if (foundAuthority.isPresent()) throw new AuthorityException("Authority already exists with name " + authority.getName());
        else {

            authority.setName(authorityName);
            for (User c : authority.getUsers()) c.getAuthorities().add(authority);

            Authority savedAuthority = aRepo.save(authority);

            return savedAuthority;
        }
    }

    // Delete an Authority based on authority_id
    @Override
    public String deleteAuthorityById(Integer authority_id) throws ResourceNotFoundException, AuthorityException {

        Optional<Authority> foundAuthority = aRepo.findById(authority_id);

        if (!foundAuthority.isPresent()) throw new ResourceNotFoundException("Authority not found with id " + authority_id);

        if (!foundAuthority.get().getUsers().isEmpty()) throw new AuthorityException("Can't delete authority, users are allocated to this authority");

        aRepo.delete(foundAuthority.get());

        return "Authority deleted successfully";
    }

    // Delete an Authority based on authority_name
    @Override
    public String deleteAuthorityByName(String authority_name) throws ResourceNotFoundException, AuthorityException {
        Optional<Authority> foundAuthority = aRepo.findByName(authority_name);

        if (!foundAuthority.isPresent()) throw new ResourceNotFoundException("Authority not found with name " + authority_name);

        if (!foundAuthority.get().getUsers().isEmpty()) throw new AuthorityException("Can't delete authority, users are allocated to this authority");

        aRepo.delete(foundAuthority.get());

        return "Authority deleted successfully";
    }

    // Retrieve an Authority based on authority_id
    @Override
    public Authority getAuthorityById(Integer authority_id) throws ResourceNotFoundException {
        return aRepo.findById(authority_id).orElseThrow(() -> new ResourceNotFoundException("Authority not found with id " + authority_id));
    }

    // Retrieve an Authority based on authority_name
    @Override
    public Authority getAuthorityByName(String authority_name) throws ResourceNotFoundException {
        Authority authority = aRepo.findByName(authority_name).orElseThrow(() -> new ResourceNotFoundException("Authority not found with name " + authority_name));
        return authority;
    }

    // Retrieve all Authorities
    @Override
    public List<Authority> getAllAuthority() throws ResourceNotFoundException {
        List<Authority> authorities = aRepo.findAll();
        if (authorities.isEmpty()) throw new ResourceNotFoundException("No Authority found");
        return authorities;
    }

    // Update Authority based on its I'd
    @Override
    public Authority updateAuthorityById(Integer authority_id, Authority authority) throws ResourceNotFoundException {
        Authority foundAuthority = aRepo.findById(authority_id).orElseThrow(() -> new ResourceNotFoundException("Authority not found with id " + authority_id));
        foundAuthority.setName(authority.getName());
        Authority savedAuthority = aRepo.save(foundAuthority);
        return savedAuthority;
    }

    // Update Authority based on its name
    @Override
    public Authority updateAuthorityByName(String name, Authority authority) throws ResourceNotFoundException {
        Authority foundAuthority = aRepo.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Authority not found with name " + name));
        foundAuthority.setName(authority.getName());
        Authority savedAuthority = aRepo.save(foundAuthority);
        return savedAuthority;
    }
}
