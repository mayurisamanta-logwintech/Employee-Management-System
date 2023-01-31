package com.security.SpringSecurity.config;

import com.security.SpringSecurity.Entity.Authority;
import com.security.SpringSecurity.Entity.Privilege;
import com.security.SpringSecurity.Exception.ResourceNotFoundException;
import com.security.SpringSecurity.Repository.AuthorityRepo;
import com.security.SpringSecurity.filters.JwtTokenGeneratorFilter;
import com.security.SpringSecurity.filters.JwtTokenValidatorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.*;

@Configuration
public class ProjectConfig {

    @Autowired
    private AuthorityRepo aRepo;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/user/signup").permitAll()
                .antMatchers("/user/login").authenticated()
                .antMatchers(("/privilege/**")).hasAuthority("ADMIN")
                .antMatchers(getPrivilege("USER")).hasAuthority("USER")
                .antMatchers(getPrivilege("ADMIN")).hasAuthority("ADMIN")
                .antMatchers(getPrivilege("HR")).hasAuthority("HR")
                .anyRequest().denyAll()
                .and()
                .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .formLogin()
                .and().httpBasic();

        return http.build();

    }

    private String[] getPrivilege (String authorityName) throws ResourceNotFoundException {
        Optional<Authority> authority = aRepo.findByName(authorityName);
        if (authority.isPresent()){

            Set<Privilege> privileges = authority.get().getPrivileges();
            String[] privilegeString = new String[privileges.size()];
            Integer index = 0;
            for (Privilege p : privileges) privilegeString[index++] = p.getName();
            return privilegeString;
        }
        else {
            String[] privilegeString = new String[0];
            return privilegeString;
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
