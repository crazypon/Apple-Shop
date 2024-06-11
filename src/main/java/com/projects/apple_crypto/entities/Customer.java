package com.projects.apple_crypto.entities;

import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force=true)
@Table(name="users")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final String firstName;

    private final String lastName;

    @CreditCardNumber(message="Not A Valid Credit Card Number")
    private final String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\\\/])([2-9][0-9])$", message="Must be formatted MM/YY")
    private final String ccExpiration;
    
}
