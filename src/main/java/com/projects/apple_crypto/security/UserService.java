package com.projects.apple_crypto.security;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projects.apple_crypto.entities.Customer;
import com.projects.apple_crypto.entities.CustomerRepository;
import com.projects.apple_crypto.entities.Role;
import com.projects.apple_crypto.entities.RoleRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Service
public class UserService implements UserDetailsService{

    @PersistenceContext
    EntityManager em;

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    RoleRepository roleRepo;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer user = customerRepo.findByFirstName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public Customer findUserById(Long userId) {
        Optional<Customer> userFromDb = customerRepo.findById(userId);
        return userFromDb.orElse(new Customer());
    }

    public List<Customer> allUsers() {
        return (List<Customer>) customerRepo.findAll();
    }

    public boolean saveUser(Customer customer) {
        Customer userFromDB = customerRepo.findByFirstName(customer.getUsername());

        if (userFromDB != null) {
            return false;
        }

        customer.setRoles(Collections.singleton(new Role("ROLE_USER")));
        customer.setPassword(bCryptPasswordEncoder().encode(customer.getPassword()));
        customerRepo.save(customer);
        return true;
    }
}

