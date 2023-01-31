package com.security.SpringSecurity.config;

import com.security.SpringSecurity.Entity.Authority;
import com.security.SpringSecurity.Repository.UserRepo;
import com.security.SpringSecurity.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class ProjectAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepo cRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();

        String password = authentication.getCredentials().toString();

        Optional<User> user = cRepo.findByEmail(username);

        if (user.isPresent()){

            if (passwordEncoder.matches(password, user.get().getPassword())){
                Authentication auth =  new UsernamePasswordAuthenticationToken(username, password, getGrantedAuthorities(user.get().getAuthorities()));
                return auth;
            }
            else {
                throw new BadCredentialsException("Invalid Password");
            }
        }
        else throw  new BadCredentialsException("No user found");

    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities){

        List<GrantedAuthority> list = new ArrayList<>();

        for (Authority a : authorities){
            list.add(new SimpleGrantedAuthority(a.getName()));
        }

        return list;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
