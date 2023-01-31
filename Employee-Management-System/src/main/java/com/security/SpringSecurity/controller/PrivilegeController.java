package com.security.SpringSecurity.controller;

import com.security.SpringSecurity.Dto.PrivilegeAuthorityDto;
import com.security.SpringSecurity.Entity.Privilege;
import com.security.SpringSecurity.Exception.ResourceNotFoundException;
import com.security.SpringSecurity.Service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This controller includes all the api related to Privilege
 * All api starts with "/privilege"
 * Communicates with Service layer (PrivilegeService) for business logic
 */
@RestController
@RequestMapping("/privilege")
public class PrivilegeController {

    @Autowired
    private PrivilegeService pSer;

    // @POST method to create Privilege = /privilege/
    // return PrivilegeAuthorityDto with a CREATED status
    @PostMapping("/")
    public ResponseEntity<PrivilegeAuthorityDto> createAuthorityHandler(@Valid @RequestBody Privilege privilege)  {
        PrivilegeAuthorityDto savedPrivilege = pSer.createPrivilege(privilege);
        return new ResponseEntity<>(savedPrivilege, HttpStatus.CREATED);
    }

    // @DELETE method to delete Privilege by taking privilege_id = /privilege/deleteById/{privilege_id}
    @DeleteMapping("/deleteById/{privilege_id}")
    public ResponseEntity<String> deletePrivilegeByIdHandler(@PathVariable("privilege_id") Integer privilege_id) throws ResourceNotFoundException {
        String message = pSer.deletePrivilegeById(privilege_id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // @GET method to get Privilege by taking privilege_id = /privilege/getById/{privilege_id}
    // return PrivilegeAuthorityDto with an OK status
    @GetMapping("/getById/{privilege_id}")
    public ResponseEntity<PrivilegeAuthorityDto> getPrivilegeByIdHandler(@PathVariable("privilege_id") Integer privilege_id) throws ResourceNotFoundException {
        PrivilegeAuthorityDto privilege = pSer.getPrivilegeById(privilege_id);
        return new ResponseEntity<>(privilege, HttpStatus.OK);
    }

    // @GET method to get list of Privilege = /privilege/getAll
    // return a list of PrivilegeAuthorityDto with an OK status
    @GetMapping("/getAll")
    public ResponseEntity<List<PrivilegeAuthorityDto>> getAllPrivilegeHandler() throws ResourceNotFoundException {
        List<PrivilegeAuthorityDto> privileges = pSer.getAllPrivileges();
        return new ResponseEntity<>(privileges, HttpStatus.OK);
    }

    // @PUT method to update Privilege by taking privilege_id = /privilege/updateById/{privilege_id}
    // return PrivilegeAuthorityDto with an OK status
    @PutMapping("/updateById/{privilege_id}")
    public  ResponseEntity<PrivilegeAuthorityDto> updateAuthorityByIdHandler(@PathVariable("privilege_id") Integer privilege_id, @RequestBody Privilege privilege) throws ResourceNotFoundException {
        PrivilegeAuthorityDto savedPrivilege = pSer.updatePrivilegeById(privilege_id, privilege);
        return new ResponseEntity<>(savedPrivilege, HttpStatus.OK);
    }

    // @PUT method to assign Authority to Privilege by taking privilege_id and authority_id = /privilege/assignById/{privilege_id}/authority/{authority_id}
    // return PrivilegeAuthorityDto with an OK status
    @PutMapping("/assignById/{privilege_id}/authority/{authority_id}")
    public ResponseEntity<PrivilegeAuthorityDto> assignAuthorityByIdHandler (@PathVariable("privilege_id") Integer privilege_id, @PathVariable("authority_id") Integer authority_id) throws ResourceNotFoundException {
        PrivilegeAuthorityDto privilege = pSer.assignPrivilegeById(privilege_id,authority_id);
        return new ResponseEntity<>(privilege, HttpStatus.OK);
    }

    // @PUT method to assign Authority to Privilege by taking privilege_id and authority_name = /privilege/assignByName/{privilege_id}/authority/{authority_name}
    // return PrivilegeAuthorityDto with an OK status
    @PutMapping("/assignByName/{privilege_id}/authority/{authority_name}")
    public ResponseEntity<PrivilegeAuthorityDto> assignAuthorityByNameHandler (@PathVariable("privilege_id") Integer privilege_id, @PathVariable("authority_name") String authority_name) throws ResourceNotFoundException {
        PrivilegeAuthorityDto privilege = pSer.assignPrivilegeByName(privilege_id, authority_name);
        return new ResponseEntity<>(privilege, HttpStatus.OK);
    }

    // @PUT method to deallocate Authority to Privilege by taking privilege_id and authority_id = /privilege/deallocateById/{privilege_id}/authority/{authority_id}
    // return PrivilegeAuthorityDto with an OK status
    @PutMapping("/deallocateById/{privilege_id}/authority/{authority_id}")
    public ResponseEntity<PrivilegeAuthorityDto> deallocateAuthorityByIdHandler(@PathVariable("privilege_id") Integer privilege_id, @PathVariable("authority_id") Integer authority_id) throws ResourceNotFoundException {
        PrivilegeAuthorityDto privilege = pSer.deallocatePrivilegeById(privilege_id, authority_id);
        return new ResponseEntity<>(privilege, HttpStatus.OK);
    }

    // @PUT method to deallocate Authority to Privilege by taking privilege_id and authority_name = /privilege/deallocateByName/{privilege_id}/authority/{authority_name}
    // return PrivilegeAuthorityDto with an OK status
    @PutMapping("/deallocateByName/{privilege_id}/authority/{authority_name}")
    public ResponseEntity<PrivilegeAuthorityDto> deallocateAuthorityByNameHandler(@PathVariable("privilege_id") Integer privilege_id, @PathVariable("authority_name") String authority_name) throws ResourceNotFoundException {
        PrivilegeAuthorityDto privilege = pSer.deallocatePrivilegeByName(privilege_id, authority_name);
        return new ResponseEntity<>(privilege, HttpStatus.OK);
    }

}
