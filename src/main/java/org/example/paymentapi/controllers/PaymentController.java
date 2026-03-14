package org.example.paymentapi.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.example.paymentapi.dto.PaymentRequest;
import org.example.paymentapi.entities.Payment;
import org.example.paymentapi.services.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentRequest request) {

        try {

            Payment payment = paymentService.createPayment(request);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(payment);

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Payment failed");

        }
    }
}