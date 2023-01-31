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
public class PrivilegeAuthorityDto {

    private Integer privilege_id;

    private String name;

    private Set<String> authorities = new HashSet<>();
}
