package com.security.SpringSecurity.Service;

import com.security.SpringSecurity.Dto.PrivilegeAuthorityDto;
import com.security.SpringSecurity.Exception.ResourceNotFoundException;
import com.security.SpringSecurity.Entity.Privilege;

import java.util.List;

/**
 * Service Interface for performing operations on Privilege
 */
public interface PrivilegeService {

    PrivilegeAuthorityDto createPrivilege (Privilege privilege);

    String deletePrivilegeById (Integer privilege_id) throws ResourceNotFoundException;

    PrivilegeAuthorityDto getPrivilegeById (Integer privilege_id) throws ResourceNotFoundException;

    List<PrivilegeAuthorityDto> getAllPrivileges () throws ResourceNotFoundException;

    PrivilegeAuthorityDto updatePrivilegeById (Integer privilege_id, Privilege privilege) throws ResourceNotFoundException;

    PrivilegeAuthorityDto assignPrivilegeById (Integer privilege_id, Integer authority_id) throws ResourceNotFoundException;

    PrivilegeAuthorityDto assignPrivilegeByName (Integer privilege_id, String authority_name) throws ResourceNotFoundException;

    PrivilegeAuthorityDto deallocatePrivilegeById (Integer privilege_id, Integer authority_id) throws ResourceNotFoundException;

    PrivilegeAuthorityDto deallocatePrivilegeByName (Integer privilege_id, String authority_name) throws ResourceNotFoundException;


}
