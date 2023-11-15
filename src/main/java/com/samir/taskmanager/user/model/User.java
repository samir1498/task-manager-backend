package com.samir.taskmanager.user.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.samir.taskmanager.task.model.Task;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="_user")
@Data
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @SequenceGenerator(
            name="user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long userId;
    private String username;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> taskList;

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
