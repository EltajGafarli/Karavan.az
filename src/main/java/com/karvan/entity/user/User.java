package com.karvan.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 50, unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @Column(length = 50)
    private String phoneNumber;

    @Column(name = "account_enable")
    private boolean enabled;

    @Column(name = "credentials_expired")
    private boolean credentialsNonExpired;

    @Column(name = "account_expired")
    private boolean accountNonExpired;

    @Column(name = "account_locked")
    private boolean locked;

    @Column(name = "is_supplier", columnDefinition = "boolean default false")
    private boolean isSupplier;

    @JsonIgnore
    @OneToOne(cascade = ALL, mappedBy = "user")
    private SupplierRequestEntity supplierRequestEntity;

    @Column(length = 20, unique = true, nullable = true)
    private String finCode;

    @JsonIgnore
    @OneToMany(cascade = {MERGE, DETACH, PERSIST, REFRESH}, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "user")
    private Set<Role> roles;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    public void addRole(Role role) {
        if(this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.add(role);
        role.setUser(this);
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this
                .roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleEnum().toString()))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return isEnabled() == enabled
                && user.isCredentialsNonExpired() == credentialsNonExpired
                && user.isAccountNonExpired() == accountNonExpired
                && user.isLocked() && locked
                && Objects.equals(user.getUserID(), userID)
                && Objects.equals(user.getEmail(), email)
                && Objects.equals(user.getFirstName(), firstName)
                && Objects.equals(user.getLastName(), lastName)
                && Objects.equals(user.getPassword(), password)
                && Objects.equals(user.getRoles(), roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                userID,
                email,
                firstName,
                lastName,
                password,
                roles,
                locked,
                accountNonExpired,
                credentialsNonExpired,
                locked
        );
    }
}
