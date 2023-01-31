package com.security.SpringSecurity.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthorityDto {

    private int user_id;

    private String name;

    private String email;

    private String mobile_number;

    private String create_dt;

    private Set<String> authorities = new HashSet<>();

}
