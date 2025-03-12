package com.spotec.tmd.nitrokms.controller;

import com.spotec.tmd.nitrokms.service.KmsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kms")
public class KmsController {
    private final KmsService kmsService;

    public KmsController(KmsService kmsService) {
        this.kmsService = kmsService;
    }

    @PostMapping("/encrypt")
    public String encrypt(@RequestParam String plaintext, @RequestParam String keyId) {
        return kmsService.encrypt(plaintext, keyId);
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestParam String ciphertext) {
        return kmsService.decrypt(ciphertext);
    }
}