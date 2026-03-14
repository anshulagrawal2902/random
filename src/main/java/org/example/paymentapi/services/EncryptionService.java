package org.example.paymentapi.services;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import java.security.Key;

import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    private static final String SECRET = "MySuperSecretKey";

    public String encrypt(String value) throws Exception {

        SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encrypted = cipher.doFinal(value.getBytes());

        return Base64.getEncoder().encodeToString(encrypted);
    }
}