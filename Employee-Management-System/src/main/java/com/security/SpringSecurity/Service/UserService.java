package com.security.SpringSecurity.Service;

import com.security.SpringSecurity.Exception.ResourceNotFoundException;
import com.security.SpringSecurity.Dto.UserAuthorityDto;
import com.security.SpringSecurity.Entity.User;
import com.security.SpringSecurity.Exception.UserException;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Service Interface for performing operations on User
 */
public interface UserService {

    UserAuthorityDto signup(User user) throws UserException;

    String login(Authentication authentication) throws ResourceNotFoundException;

    UserAuthorityDto assignAuthorityById (String email, Integer authority_id) throws ResourceNotFoundException;

    UserAuthorityDto assignAuthorityByName (String email, String authority_name) throws ResourceNotFoundException;

    String deleteUserById (Integer user_id) throws ResourceNotFoundException;

    String deleteUserByEmail (String email) throws ResourceNotFoundException;

    UserAuthorityDto deallocateAuthorityById (String email, Integer authority_id) throws ResourceNotFoundException;

    UserAuthorityDto deallocateAuthorityByName (String email, String authority_name) throws ResourceNotFoundException;

    UserAuthorityDto getUserById (Integer id) throws ResourceNotFoundException;

    UserAuthorityDto getUserByEmail (String email) throws ResourceNotFoundException;

    List<UserAuthorityDto> getAllUser () throws ResourceNotFoundException;

    UserAuthorityDto updateUserById (Integer id, User user) throws ResourceNotFoundException;

    UserAuthorityDto updateUserByEmail (String email, User user) throws ResourceNotFoundException;




}
