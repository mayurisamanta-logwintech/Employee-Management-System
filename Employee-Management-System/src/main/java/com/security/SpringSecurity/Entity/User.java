package com.security.SpringSecurity.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, message = "Name should contain at least 3 characters")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Mobile Number cannot be null")
    @Size(min = 10, max = 10, message = "Mobile number should be of 10 digits")
    private String mobile_number;

    @NotNull(message = "Password cannot be null")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonIgnore
    private String create_dt;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    private Set<Authority> authorities = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_id == user.user_id && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(mobile_number, user.mobile_number) && Objects.equals(password, user.password) && Objects.equals(create_dt, user.create_dt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, name, email, mobile_number, password, create_dt);
    }
}
