package com.spotec.tmd.nitrokms.controller;

import com.spotec.tmd.nitrokms.service.LambdaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/kms")
public class KmsController {


    private final LambdaService lambdaService;

    @Autowired
    public KmsController(LambdaService lambdaService) {
        this.lambdaService = lambdaService;
    }

    @PostMapping("/encrypt")
    public String encryptData(@RequestParam String plaintext, @RequestParam String keyId) {
        String encrypt = lambdaService.encrypt(plaintext, keyId);
        return encrypt;
    }

    @PostMapping("/decrypt")
    public String decryptData(@RequestParam String ciphertext) {
        String decrypt = lambdaService.decrypt(ciphertext);
        return decrypt;
    }
}