package com.security.SpringSecurity.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int authority_id;

    @NotNull(message = "Authority name cannot be null")
    @Size(min = 2, message = "Authority name should contain At least 2 characters")
    @Column(unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authorities")
    private Set<User> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authorities")
    private Set<Privilege> privileges = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return authority_id == authority.authority_id && Objects.equals(name, authority.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority_id, name);
    }
}
