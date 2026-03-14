package org.example.paymentapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String zipCode;

    @Column(length = 2048)
    private String encryptedCardNumber;

    public String getFirstName(){
        return this.firstName;
    }

    public String setFirstName(String name){
        return this.firstName = name;
    }

    private LocalDateTime createdAt = LocalDateTime.now();
}