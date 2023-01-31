package com.security.SpringSecurity.controller;

import com.security.SpringSecurity.Dto.UserAuthorityDto;
import com.security.SpringSecurity.Entity.User;
import com.security.SpringSecurity.Exception.ResourceNotFoundException;
import com.security.SpringSecurity.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller includes all the api related to User
 * All api starts with "/user"
 * Communicates with Service layer (UserService) for business logic
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService uSer;

    // @PUT method to assign Authority to User by taking user email and authority_id = /user/assignById/{email}/authority/{authority_id}
    // return UserAuthorityDto with an OK status
    @PutMapping("/assignById/{email}/authority/{authority_id}")
    public ResponseEntity<UserAuthorityDto> assignAuthorityByIdHandler (@PathVariable("email") String email, @PathVariable("authority_id") Integer authority_id) throws ResourceNotFoundException {
        UserAuthorityDto assignedUser = uSer.assignAuthorityById(email, authority_id);
        return new ResponseEntity<>(assignedUser, HttpStatus.OK);
    }

    // @PUT method to assign Authority to User by taking user email and authority_name = /user/assignByName/{email}/authority/{authority_name}
    // return UserAuthorityDto with an OK status
    @PutMapping("/assignByName/{email}/authority/{authority_name}")
    public ResponseEntity<UserAuthorityDto> assignAuthorityByNameHandler (@PathVariable("email") String email, @PathVariable("authority_name") String authority_name) throws ResourceNotFoundException {
        UserAuthorityDto assignedUser = uSer.assignAuthorityByName(email, authority_name);
        return new ResponseEntity<>(assignedUser, HttpStatus.OK);
    }

    // @DELETE method to delete User by taking user_id = /user/deleteById/{user_id}
    @DeleteMapping("/deleteById/{user_id}")
    public ResponseEntity<String> deleteUserByIdHandler (@PathVariable("user_id") Integer user_id) throws ResourceNotFoundException {
        String message = uSer.deleteUserById(user_id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // @DELETE method to delete User by taking user email = /user/deleteByEmail/{email}
    @DeleteMapping("/deleteByEmail/{email}") //working
    public ResponseEntity<String> deleteUserByEmailHandler (@PathVariable("email") String email) throws ResourceNotFoundException {
        String message = uSer.deleteUserByEmail(email);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // @PUT method to deallocate Authority to User by taking user email and authority_id = /user/deallocateById/{email}/authority/{authority_id}
    // return UserAuthorityDto with an OK status
    @PutMapping("/deallocateById/{email}/authority/{authority_id}")
    public ResponseEntity<UserAuthorityDto> deallocateAuthorityByIdHandler(@PathVariable("email") String email, @PathVariable("authority_id") Integer authority_id) throws ResourceNotFoundException {
        UserAuthorityDto user = uSer.deallocateAuthorityById(email, authority_id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // @PUT method to deallocate Authority to User by taking user email and authority_name = /user/deallocateByName/{email}/authority/{authority_name}
    // return UserAuthorityDto with an OK status
    @PutMapping("/deallocateByName/{email}/authority/{authority_name}")
    public ResponseEntity<UserAuthorityDto> deallocateAuthorityByNameHandler(@PathVariable("email") String email, @PathVariable("authority_name") String authority_name) throws ResourceNotFoundException {
        UserAuthorityDto user = uSer.deallocateAuthorityByName(email,authority_name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // @GET method to retrieve User by taking user_id = /user/getById/{user_id}
    // return UserAuthorityDto with an OK status
    @GetMapping("/getById/{user_id}") //working
    public ResponseEntity<UserAuthorityDto> getUserByIdHandler(@PathVariable("user_id") Integer user_id) throws ResourceNotFoundException {
        UserAuthorityDto user = uSer.getUserById(user_id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // @GET method to retrieve User by taking user email = /user/getByEmail/{email}
    // return UserAuthorityDto with an OK status
    @GetMapping("/getByEmail/{email}") //working
    public ResponseEntity<UserAuthorityDto> getUserByEmailHandler(@PathVariable("email") String email) throws ResourceNotFoundException {
        UserAuthorityDto user = uSer.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // @GET method to retrieve list of User = /user/getAll
    // return list of UserAuthorityDto with an OK status
    @GetMapping("/getAll")
    public ResponseEntity<List<UserAuthorityDto>> getAllUserHandler () throws ResourceNotFoundException {
        List<UserAuthorityDto> dtos = uSer.getAllUser();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // @PUT method to update User by taking user_id = /user/updateById/{user_id}
    // return UserAuthorityDto with an OK Status
    @PutMapping("/updateById/{user_id}")
    public ResponseEntity<UserAuthorityDto> updateUserByIdHandler (@PathVariable("user_id") Integer user_id , @RequestBody User user) throws ResourceNotFoundException {
        UserAuthorityDto dto = uSer.updateUserById(user_id,user);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // @PUT method to update User by taking user email = /user/updateByEmail/{email}
    // return UserAuthorityDto with an OK Status
    @PutMapping("/updateByEmail/{email}")
    public ResponseEntity<UserAuthorityDto> updateUserByEmailHandler (@PathVariable("email") String email , @RequestBody User user) throws ResourceNotFoundException {
        UserAuthorityDto dto = uSer.updateUserByEmail(email,user);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
