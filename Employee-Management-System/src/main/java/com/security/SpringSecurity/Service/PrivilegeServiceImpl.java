package com.security.SpringSecurity.Service;

import com.security.SpringSecurity.Dto.PrivilegeAuthorityDto;
import com.security.SpringSecurity.Exception.ResourceNotFoundException;
import com.security.SpringSecurity.Repository.AuthorityRepo;
import com.security.SpringSecurity.Entity.Authority;
import com.security.SpringSecurity.Entity.Privilege;
import com.security.SpringSecurity.Repository.PrivilegeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service class for handling business logic related to Privilege
 * Provides the implementation of PrivilegeService Interface
 * communicates with the repository layer (PrivilegeRepo and AuthorityRepo) for database access.
 */
@Service
public class PrivilegeServiceImpl implements PrivilegeService{

    @Autowired
    private PrivilegeRepo pRepo;

    @Autowired
    private AuthorityRepo aRepo;

    // Save new Privilege by taking privilege object
    @Override
    public PrivilegeAuthorityDto createPrivilege(Privilege privilege) {
        Privilege savedPrivilege = pRepo.save(privilege);
        return privilegeToDto(savedPrivilege);
    }

    // Delete Privilege based on its I'd
    @Override
    public String deletePrivilegeById(Integer privilege_id) throws ResourceNotFoundException {
        Privilege privilege = pRepo.findById(privilege_id).orElseThrow(() -> new ResourceNotFoundException("Privilege not found with id " + privilege_id));
        pRepo.delete(privilege);
        return "Privilege Deleted Successfully";
    }

    // Retrieve Privilege based on its I'd
    @Override
    public PrivilegeAuthorityDto getPrivilegeById(Integer privilege_id) throws ResourceNotFoundException {
        Privilege privilege = pRepo.findById(privilege_id).orElseThrow(() -> new ResourceNotFoundException("Privilege not found with id " + privilege_id));
        return privilegeToDto(privilege);
    }

    // Retrieve all Privileges
    @Override
    public List<PrivilegeAuthorityDto> getAllPrivileges() throws ResourceNotFoundException {
        List<Privilege> privileges = pRepo.findAll();
        if (privileges.isEmpty()) throw new ResourceNotFoundException("No Privilege found");
        List<PrivilegeAuthorityDto> dtos = new ArrayList<>();
        for (Privilege p : privileges) dtos.add(privilegeToDto(p));
        return dtos;
    }

    // Update Privilege based on its I'd
    @Override
    public PrivilegeAuthorityDto updatePrivilegeById(Integer privilege_id, Privilege privilege) throws ResourceNotFoundException {
        Privilege foundPrivilege = pRepo.findById(privilege_id).orElseThrow(() -> new ResourceNotFoundException("Privilege not found with id " + privilege_id));
        foundPrivilege.setName(privilege.getName());
        Privilege savedPrivilege = pRepo.save(foundPrivilege);
        return privilegeToDto(savedPrivilege);
    }

    // Assign Authority to Privilege based on privilege_id and authority_id
    @Override
    public PrivilegeAuthorityDto assignPrivilegeById(Integer privilege_id, Integer authority_id) throws ResourceNotFoundException {
        Privilege privilege = pRepo.findById(privilege_id).orElseThrow(() -> new ResourceNotFoundException("Privilege not found with id " + privilege_id));
        Authority authority = aRepo.findById(authority_id).orElseThrow(() -> new ResourceNotFoundException("Authority not found with id " + authority_id));

        privilege.getAuthorities().add(authority);
        authority.getPrivileges().add(privilege);

        Privilege savedPrivilege = pRepo.save(privilege);

        return privilegeToDto(savedPrivilege);
    }

    // Assign Authority to Privilege based on privilege_id and authority_name
    @Override
    public PrivilegeAuthorityDto assignPrivilegeByName(Integer privilege_id, String authority_name) throws ResourceNotFoundException {
        Privilege privilege = pRepo.findById(privilege_id).orElseThrow(() -> new ResourceNotFoundException("Privilege not found with id " + privilege_id));
        Authority authority = aRepo.findByName(authority_name).orElseThrow(() -> new ResourceNotFoundException("Authority not found with name " + authority_name));

        privilege.getAuthorities().add(authority);
        authority.getPrivileges().add(privilege);

        Privilege savedPrivilege = pRepo.save(privilege);

        return privilegeToDto(savedPrivilege);
    }

    // Deallocate Authority to Privilege based on privilege_id and authority_id
    @Override
    public PrivilegeAuthorityDto deallocatePrivilegeById(Integer privilege_id, Integer authority_id) throws ResourceNotFoundException {
        Privilege privilege = pRepo.findById(privilege_id).orElseThrow(() -> new ResourceNotFoundException("Privilege not found with id " + privilege_id));
        Authority authority = aRepo.findById(authority_id).orElseThrow(() -> new ResourceNotFoundException("Authority not found with id " + authority_id));

        privilege.getAuthorities().removeIf(a -> a.getName().equals(authority.getName()));
        authority.getPrivileges().removeIf(p -> p.getName().equals(privilege.getName()));

        Privilege savedPrivilege = pRepo.save(privilege);

        return privilegeToDto(savedPrivilege);

    }

    // Deallocate Authority to Privilege based on privilege_id and authority_name
    @Override
    public PrivilegeAuthorityDto deallocatePrivilegeByName(Integer privilege_id, String authority_name) throws ResourceNotFoundException {
        Privilege privilege = pRepo.findById(privilege_id).orElseThrow(() -> new ResourceNotFoundException("Privilege not found with id " + privilege_id));
        Authority authority = aRepo.findByName(authority_name).orElseThrow(() -> new ResourceNotFoundException("Authority not found with name " + authority_name));

        privilege.getAuthorities().removeIf(a -> a.getName().equals(authority.getName()));
        authority.getPrivileges().removeIf(p -> p.getName().equals(privilege.getName()));

        Privilege savedPrivilege = pRepo.save(privilege);

        return privilegeToDto(savedPrivilege);
    }

    // Method to convert Privilege to PrivilegeAuthorityDto
    private PrivilegeAuthorityDto privilegeToDto (Privilege privilege){
        Set<String> authorities = new HashSet<>();
        for (Authority authority : privilege.getAuthorities()) authorities.add(authority.getName());
        return new PrivilegeAuthorityDto(privilege.getPrivilege_id(), privilege.getName(), authorities);
    }
}
