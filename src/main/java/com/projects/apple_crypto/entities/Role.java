package com.projects.apple_crypto.entities;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "user_role")
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<Customer> customers;

    @Override
    public String getAuthority() {
        return getName();
    }

    public Role(String roleName) {
        this.name = roleName;
    }
}

