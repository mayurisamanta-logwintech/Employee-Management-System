package com.security.SpringSecurity.controller;

import com.security.SpringSecurity.Exception.AuthorityException;
import com.security.SpringSecurity.Exception.ResourceNotFoundException;
import com.security.SpringSecurity.Entity.Authority;
import com.security.SpringSecurity.Service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This controller includes all the api related to authority
 * All api starts with "/authority"
 * Communicates with Service layer (AuthorityService) for business logic
 */

@RestController
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    private AuthorityService aSer;

    // @POST method to create Authority = /authority/
    // return Authority with a CREATED status
    @PostMapping("/")
    public ResponseEntity<Authority> createAuthorityHandler(@Valid @RequestBody Authority authority) throws AuthorityException {
        Authority savedAuthority = aSer.createAuthority(authority);
        return new ResponseEntity<>(savedAuthority, HttpStatus.CREATED);
    }

    // @DELETE method to delete Authority by taking authority_id = /authority/deleteById/{authority_id}
    @DeleteMapping("/deleteById/{authority_id}")
    public ResponseEntity<String> deleteauthorityByIdHandler(@PathVariable("authority_id") Integer authority_id) throws ResourceNotFoundException, AuthorityException {
        String message = aSer.deleteAuthorityById(authority_id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // @DELETE method to delete Authority by taking authority_name = /authority/deleteByName/{authority_name}
    @DeleteMapping("/deleteByName/{authority_name}")
    public ResponseEntity<String> deleteauthorityByNameHandler(@PathVariable("authority_name") String authority_name) throws ResourceNotFoundException, AuthorityException {
        String message = aSer.deleteAuthorityByName(authority_name);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // @GET method to retrieve Authority by taking authority_id = /authority/getById/{authority_id}
    // return Authority with an OK status
    @GetMapping("/getById/{authority_id}")
    public ResponseEntity<Authority> getauthorityByIdHandler(@PathVariable("authority_id") Integer authority_id) throws ResourceNotFoundException {
        Authority authority = aSer.getAuthorityById(authority_id);
        return new ResponseEntity<>(authority, HttpStatus.OK);
    }

    // @GET method to retrieve Authority by taking authority_name = /authority/getByName/{authority_name}
    // return Authority with an OK status
    @GetMapping("/getByName/{authority_name}")
    public ResponseEntity<Authority> getauthorityByNameHandler(@PathVariable("authority_name") String authority_name) throws ResourceNotFoundException {
        Authority authority = aSer.getAuthorityByName(authority_name);
        return new ResponseEntity<>(authority, HttpStatus.OK);
    }

    // @GET method to retrieve all Authority = /authority/getAll
    // return list of Authority with an OK status
    @GetMapping("/getAll")
    public ResponseEntity<List<Authority>> getAllAuthorityHandler() throws ResourceNotFoundException {
        List<Authority> authorities = aSer.getAllAuthority();
        return new ResponseEntity<>(authorities, HttpStatus.OK);
    }

    // @PUT method to update Authority by taking authority_id = /authority/updateById/{authority_id}
    // return Authority with an OK status
    @PutMapping("/updateById/{authority_id}")
    public  ResponseEntity<Authority> updateAuthorityByIdHandler(@PathVariable("authority_id") Integer authority_id, @RequestBody Authority authority) throws ResourceNotFoundException {
        Authority savedAuthority = aSer.updateAuthorityById(authority_id, authority);
        return new ResponseEntity<>(savedAuthority, HttpStatus.OK);
    }

    // @PUT method to update Authority by taking authority_name = /authority/updateByName/{authority_name}
    // return Authority with an OK status
    @PutMapping("/updateByName/{authority_name}")
    public  ResponseEntity<Authority> updateAuthorityByName(@PathVariable("authority_name") String authority_name, @RequestBody Authority authority) throws ResourceNotFoundException {
        Authority savedAuthority = aSer.updateAuthorityByName(authority_name, authority);
        return new ResponseEntity<>(savedAuthority, HttpStatus.OK);
    }

}
