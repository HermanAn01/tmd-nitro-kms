package com.spotec.tmd.nitrokms.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crypto")
public class CryptoController {

    @Autowired
    private AwsSecretsManagerService secretsManagerService;

    @GetMapping("/private-key")
    public String getPrivateKey() {
        return secretsManagerService.getPrivateKey();
    }
}

