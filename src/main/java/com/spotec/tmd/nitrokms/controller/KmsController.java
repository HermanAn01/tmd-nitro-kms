package com.spotec.tmd.nitrokms.controller;

import com.spotec.tmd.nitrokms.model.DecryptBean;
import com.spotec.tmd.nitrokms.model.EncryptBean;
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
    public String encryptData(@RequestBody EncryptBean encryptBean) {
        String encrypt = lambdaService.encrypt(encryptBean.getPlaintext(), encryptBean.getKeyId());
        return encrypt;
    }

    @PostMapping("/decrypt")
    public String decryptData(@RequestBody DecryptBean decryptBean) {
        String decrypt = lambdaService.decrypt(decryptBean.getCiphertext());
        return decrypt;
    }
}