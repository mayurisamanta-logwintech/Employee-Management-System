package com.security.SpringSecurity.Service;

import com.security.SpringSecurity.Exception.ResourceNotFoundException;
import com.security.SpringSecurity.Repository.AuthorityRepo;
import com.security.SpringSecurity.Dto.UserAuthorityDto;
import com.security.SpringSecurity.Entity.Authority;
import com.security.SpringSecurity.Entity.User;
import com.security.SpringSecurity.Exception.UserException;
import com.security.SpringSecurity.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * Service class for handling business logic related to User operations.
 * Provides the implementation of UserService Interface
 * communicates with the repository layer (UserRepo and AuthorityRepo) for database access.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo uRepo;

    @Autowired
    private AuthorityRepo aRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Save new User in database by taking User object
    @Override
    public UserAuthorityDto signup(User user) throws UserException {

        Optional<User> foundUser = uRepo.findByEmail(user.getEmail());
        if (foundUser.isPresent()) throw new UserException("User already exists with email " + user.getEmail());

        String BcryptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(BcryptPassword);
        user.setCreate_dt(LocalDate.now().toString());

        User savedUser = uRepo.save(user);
        return userToAuthDto(savedUser);
    }

    // Login with the application by providing valid credentials
    @Override
    public String login(Authentication authentication) throws ResourceNotFoundException {

        User user = uRepo.findByEmail(authentication.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found with email " + authentication.getName()));

        return "Login Successful";

    }

    // Assign authority to User by taking user email and authority_id
    @Override
    public UserAuthorityDto assignAuthorityById(String email, Integer authority_id) throws ResourceNotFoundException {

        Optional<User> user = uRepo.findByEmail(email);
        if (!user.isPresent()) throw new ResourceNotFoundException("User not found with email " + email);

        Authority authority = aRepo.findById(authority_id).orElseThrow(() ->
                new ResourceNotFoundException("Authority not found with id " + authority_id));

        user.get().getAuthorities().add(authority);
        authority.getUsers().add(user.get());
        aRepo.save(authority);

        return userToAuthDto(user.get());
    }

    // Assign authority to User by taking user email and authority_name
    @Override
    public UserAuthorityDto assignAuthorityByName(String email, String authority_name) throws ResourceNotFoundException {

        Optional<User> user = uRepo.findByEmail(email);
        if (!user.isPresent()) throw new ResourceNotFoundException("User not found with email " + email);

        Authority authority = aRepo.findByName(authority_name.toUpperCase()).orElseThrow(() ->
                new ResourceNotFoundException("Authority not found with name " + authority_name));

        user.get().getAuthorities().add(authority);
        authority.getUsers().add(user.get());
        aRepo.save(authority);

        return userToAuthDto(user.get());
    }

    // Delete user based on its Id
    @Override
    public String deleteUserById(Integer user_id) throws ResourceNotFoundException {

        Optional<User> user = uRepo.findById(user_id);
        if (!user.isPresent()) throw new ResourceNotFoundException("User not found with id " + user_id);

        uRepo.deleteById(user_id);

        return "User Deleted Successfully";

    }

    // Delete user based on Email
    @Override
    public String deleteUserByEmail(String email) throws ResourceNotFoundException {

        Optional<User> user = uRepo.findByEmail(email);
        if (!user.isPresent()) throw new ResourceNotFoundException("User not found with email " + email);

        uRepo.delete(user.get());

        return "User Deleted Successfully";
    }

    // Deallocate Authority to User based on email and authority_id
    @Override
    public UserAuthorityDto deallocateAuthorityById(String email, Integer authority_id) throws ResourceNotFoundException {
        User user = uRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));

        Authority authority = aRepo.findById(authority_id).orElseThrow(() ->
                new ResourceNotFoundException("Authority not found with id " + authority_id));

        user.getAuthorities().removeIf(a -> a.getName().equals(authority.getName()));
        authority.getUsers().removeIf(u -> u.getEmail().equals(email));

        User savedUser = uRepo.save(user);


        return userToAuthDto(savedUser);
    }

    // Deallocate Authority to User based on email and authority_name
    @Override
    public UserAuthorityDto deallocateAuthorityByName(String email, String authority_name) throws ResourceNotFoundException {
        User user = uRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));

        Authority authority = aRepo.findByName(authority_name).orElseThrow(() ->
                new ResourceNotFoundException("Authority not found with name " + authority_name));

        user.getAuthorities().removeIf(a -> a.getName().equals(authority.getName()));
        authority.getUsers().removeIf(u -> u.getEmail().equals(email));

        User savedUser = uRepo.save(user);

        return userToAuthDto(savedUser);
    }

    // Retrieve User based on its I'd
    @Override
    public UserAuthorityDto getUserById(Integer id) throws ResourceNotFoundException {
        User user = uRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return userToAuthDto(user);
    }

    // Retrieve User based on its Email
    @Override
    public UserAuthorityDto getUserByEmail(String email) throws ResourceNotFoundException {
        User user = uRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));
        return userToAuthDto(user);
    }

    // Retrieve all User
    @Override
    public List<UserAuthorityDto> getAllUser() throws ResourceNotFoundException {
        List<User> users = uRepo.findAll();
        if (users.isEmpty()) throw new ResourceNotFoundException("No User Found");
        List<UserAuthorityDto> dtos = new ArrayList<>();
        for (User u : users) dtos.add(userToAuthDto(u));
        return dtos;
    }

    // Update User based on its I'd
    @Override
    public UserAuthorityDto updateUserById(Integer id, User user) throws ResourceNotFoundException {
        User foundUser = uRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        if (user.getName() != null) foundUser.setName(user.getName());
        if (user.getMobile_number() != null) foundUser.setMobile_number(user.getMobile_number());
        User savedUser = uRepo.save(foundUser);
        return userToAuthDto(savedUser);
    }

    // Update User based on its Email
    @Override
    public UserAuthorityDto updateUserByEmail(String email, User user) throws ResourceNotFoundException {
        User foundUser = uRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));
        if (user.getName() != null) foundUser.setName(user.getName());
        if (user.getMobile_number() != null) foundUser.setMobile_number(user.getMobile_number());
        User savedUser = uRepo.save(foundUser);
        return userToAuthDto(savedUser);
    }

    // Method to convert User to UserAuthorityDto
    private UserAuthorityDto userToAuthDto(User user){
        Set<String> authorities = new HashSet<>();
        for (Authority a : user.getAuthorities()) authorities.add(a.getName());

        return new UserAuthorityDto(user.getUser_id(),user.getName(), user.getEmail(),
                user.getMobile_number(),user.getCreate_dt(), authorities);
    }
}
