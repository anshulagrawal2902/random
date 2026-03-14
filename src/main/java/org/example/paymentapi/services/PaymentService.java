package org.example.paymentapi.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;

import org.example.paymentapi.dto.PaymentRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.example.paymentapi.entities.Payment;

import org.example.paymentapi.services.EncryptionService;
import org.example.paymentapi.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(PaymentRequest request) throws Exception {

        Payment payment = new Payment();

        payment.setFirstName(request.getFirstName());
        payment.setLastName(request.getLastName());
        payment.setZipCode(request.getZipCode());

        String encrypted = encryptionService.encrypt(request.getCardNumber());

        payment.setEncryptedCardNumber(encrypted);

        Payment saved = paymentRepository.save(payment);

        return saved;
    }
}