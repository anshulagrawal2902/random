package com.example.payment.dto;

import lombok.Data;

@Data
public class PaymentRequest {

    private String firstName;
    private String lastName;
    private String zipCode;
    private String cardNumber;
}